/*
 * files2pdb.java
 *      Convert a pilot pdb database to files.
 *	Usage: java files2pdb foo.pdb bar
 *
 * Ken Shirriff         ken.shirriff@eng.sun.com
 *
 * Copyright (c) 1996 Sun Microsystems, Inc. All Rights Reserved.
 *
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 *
 * Please refer to the file "license.txt"
 * for further important copyright and licensing information.
 */

import java.io.*;
import java.util.*;
import java.text.*;

public class files2pdb {
  private static final NumberFormat vmf=new DecimalFormat("00");

  public static void main(String args[]) {
    try {
      int vm=0;
      String infofile = null;
      byte name[] = new byte[32];
      short attributes=(short)0x8000;
      short version=1;
      int creationDate=0xb08823ad;
      int modificationDate=0xb08823ad;
      int lastBackupDate=0xb08823ad;
      int modificationNumber=1;
      int appInfoID=0;
      int sortInfoID=0;
      byte type[] = new byte[4];
      byte creator[] = new byte[4];
      int uniqueIDSeed=0;
      int argsidx;
      for (argsidx=0; argsidx<args.length-2; argsidx+=2) {
//	if (args[argsidx].equals("-argsidx")) {
//	  infofile = args[argsidx+1];
//	  appInfoID = (int)(new File(infofile)).length();
//	} else if (args[argsidx].equals("-n")) {
//	  int l = args[argsidx+1].length();
//	  args[argsidx+1].getBytes(0, l, name, 0);
//	  name[l] = '\0';
//	} else if (args[argsidx].equals("-a")) {
//	  attributes = (short)Integer.parseInt(args[argsidx+1]);
//	} else if (args[argsidx].equals("-v")) {
//	  version = (short)Integer.parseInt(args[argsidx+1]);
//	} else
	if (args[argsidx].equals("-cd")) {
	  creationDate = (int)Integer.parseInt(args[argsidx+1]);
	} else if (args[argsidx].equals("-md")) {
	  modificationDate = (int)Integer.parseInt(args[argsidx+1]);
	} else if (args[argsidx].equals("-bd")) {
	  lastBackupDate = (int)Integer.parseInt(args[argsidx+1]);
	} else if (args[argsidx].equals("-mn")) {
	  modificationNumber = (int)Integer.parseInt(args[argsidx+1]);
	} else if (args[argsidx].equals("-vm")) {
	  vm = (int)Integer.parseInt(args[argsidx+1]);
//	} else if (args[argsidx].equals("-t")) {
//	  args[argsidx+1].getBytes(0, 4, type, 0);
//	} else if (args[argsidx].equals("-c")) {
//	  args[argsidx+1].getBytes(0, 4, creator, 0);
//	} else if (args[argsidx].equals("-s")) {
//	  uniqueIDSeed = (int)Integer.parseInt(args[argsidx+1]);
	} else if (args[argsidx].charAt(0) == '-') {
	  usage();
	} else {
	  break;
	}
      }

      if (args.length-argsidx<1) {
	usage();
      }

      String filename = "meditor";

	StringBuffer sb=new StringBuffer();
	for(int i=0;i<filename.length();i++) {
		sb.append('\0').append(filename.charAt(i));
	}
	infofile=sb.toString();
      appInfoID = infofile.length();
      {
	String s=filename+"-VM"+vmf.format(vm);
	int l = s.length();
	s.getBytes(0, l, name, 0);
	name[l] = '\0';
      }
      attributes=72;
      version=0;
      {
	String s="RMSd";
	s.getBytes(0, 4, type, 0);
      }
      {
	String s="VM"+vmf.format(vm);
	s.getBytes(0, 4, creator, 0);
      }

      String outfile = filename+"-VM"+vmf.format(vm)+".pdb";
      DataOutputStream os = new DataOutputStream(new FileOutputStream(outfile));

      int numRecords = args.length-argsidx;
      int offset = 80+numRecords*8;

//      if (infofile != null) {
//	appInfoID += offset;
//	offset = appInfoID;
//      }

      // DatabaseHdrType
      os.write(name);
      os.writeShort(attributes);
      os.writeShort(version);
      os.writeInt(creationDate);
      os.writeInt(modificationDate);
      os.writeInt(lastBackupDate);
      os.writeInt(modificationNumber);
//      os.writeInt(appInfoID);
      os.writeInt(offset);
      offset += appInfoID;
      os.writeInt(sortInfoID);
      os.write(type);
      os.write(creator);
      os.writeInt(uniqueIDSeed);

      // RecordListType
      int nextRecordListID = 0;
      os.writeInt(nextRecordListID);
      os.writeShort(numRecords);
      for (int i=0; i<numRecords; i++) {
	int id=i+1;
	os.writeInt(offset); // LocalChunkID
	os.writeByte(0); // attributes
	os.writeByte(id>>16); // uniqueID
	os.writeByte(id>>8); // uniqueID
	os.writeByte(id>>0); // uniqueID
	offset += getFileLength(args[argsidx+i]);
      }
      os.writeShort(0); // pad
      if (infofile != null) {
        writeInfo(os, infofile);
      }
      for (int i=0; i<numRecords; i++) {
	System.out.println(args[argsidx+i]);
	writeFile(os, args[argsidx+i]);
      }
      os.close();

    } catch (IOException e) {
//      System.out.println("IOException "+e);
	e.printStackTrace();
    }
  }

  static void writeInfo(DataOutputStream os, String name) throws IOException {
    byte buf[] = name.getBytes();
    os.write(buf, 0, buf.length);
  }

  static int getFileLength(String name) throws IOException {
    ByteArrayOutputStream os=new ByteArrayOutputStream();
    writeFile(new DataOutputStream(os), name);
    os.close();
    return os.toByteArray().length;
  }

  static void writeFile(DataOutputStream os, String name) throws IOException {
    String s = getFile(name);
    if (name.endsWith(".txt") || name.endsWith(".TXT")) {
      name = name.substring(0, name.length()-4);
    }
    os.writeUTF(name);
    os.writeUTF(s);
  }

  static String getFile(String name) throws IOException {
    ByteArrayOutputStream os=new ByteArrayOutputStream();
    byte buf[] = new byte[1024];
    DataInputStream is = new DataInputStream(new FileInputStream(name));
    try {
      while (true) {
	int read = is.read(buf);
	if (read < 0) {
	  break;
	}
	os.write(buf, 0, read);
      }
    } catch (EOFException e) {}
    is.close();
    os.close();
    return os.toString();
  }

  static void usage() {
//    System.out.println("Usage: java files2pdb [-i appInfoFile] [-n name] [-a attributes] [-v version] [-cd creationdate] [-md modificationdate] [-bd backupDate] [-mn modificationNumber] [-t type ] [-c creator] [-s seed] outfile recordFiles(s)");
    System.out.println("Usage: java files2pdb [-cd creationdate] [-md modificationdate] [-bd backupDate] [-mn modificationNumber] [-vm midletSuite] recordFile(s)");
    System.exit(-1);
  }
}
