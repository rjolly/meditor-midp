/*
 * pdb2files.java
 *      Convert a pilot pdb database to files.
 *	Usage: java pdb2files foo.pdb bar
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

public class pdb2files {
  public static void main(String args[]) {
    try {
      File f;
      String src=null, dst=null;
      if (args.length == 1) {
	src = args[0];
	dst = args[0];
	if (dst.endsWith(".pdb") || dst.endsWith(".PDB")) {
	  dst = dst.substring(0, dst.length()-4);
	}
//      } else if (args.length == 2) {
//	src = args[0];
//	dst = args[1];
      } else {
//	System.out.println("Usage: java pdb2files foo.pdb [outfile]");
	System.out.println("Usage: java pdb2files foo.pdb");
	System.exit(-1);
      }

//      System.out.println("src ="+src+" "+dst);
      f = new File(src);
      DataInputStream is = new DataInputStream(new FileInputStream(f));

      // DatabaseHdrType
      byte name[] = new byte[32];
      is.read(name);
      short attributes = is.readShort();
      short version = is.readShort();
      int creationDate = is.readInt();
      int modificationDate = is.readInt();
      int lastBackupDate = is.readInt();
      int modificationNumber = is.readInt();
      int appInfoID = is.readInt();
      int sortInfoID = is.readInt();
      byte type[] = new byte[4];
      is.read(type);
      byte creator[] = new byte[4];
      is.read(creator);
      int uniqueIDSeed = is.readInt();
//      System.out.println("name "+(new String(name)));
//      System.out.println("version "+version);
//      System.out.println("attributes "+attributes);
//      System.out.println("type "+(new String(type)));
//      System.out.println("creator "+(new String(creator)));
//      System.out.println("creationDate "+creationDate);
//      System.out.println("modificationDate "+modificationDate);
//      System.out.println("lastBackupDate "+lastBackupDate);
//      System.out.println("modificationNumber "+modificationNumber);
//      System.out.println("appInfoID "+appInfoID);
//      System.out.println("sortInfoID "+sortInfoID);
//      System.out.println("uniqueIDSeed "+uniqueIDSeed);

      // RecordListType
      int nextRecordListID = is.readInt();
      short numRecords = is.readShort();
//      System.out.println("numRecords "+numRecords);
//      System.out.println("nextRecordListID "+nextRecordListID);
      if (appInfoID != 0) {
	Record r = new Record(dst+"-info");
	r.localChunkID = appInfoID;
	v.addElement(r);
      }
      for (int i=0; i<numRecords; i++) {
	readRecord(is, dst+i);
      }
      is.readShort(); // pad
      int offset = 80+numRecords*8;
      int totNumRecords = (short)v.size();
//      System.out.println("totNumRecords "+totNumRecords);
      for (int i=0; i<totNumRecords; i++) {
	int end;
	Record r = (Record)v.elementAt(i);
	int start = r.localChunkID;
	if (i == totNumRecords-1) {
	  end = (int)f.length();
	} else {
	  end = ((Record)v.elementAt(i+1)).localChunkID;
	}
	if (offset != start) {
//	  System.out.println("Offset "+start+" vs "+offset);
	}
	if(i==0) skipInfo(is, end-start);
	else writeFile(is, r.name, end-start);
	offset += end-start;
      }

    } catch (IOException e) {
//      System.out.println("IOException "+e);
	e.printStackTrace();
    }
  }

  static void skipInfo(DataInputStream is, int bytes) throws IOException {
    byte buf[] = new byte[1024];
    while (bytes > 0) {
      int read = is.read(buf,0,bytes>1024?1024:bytes);
      bytes -= read;
    }
  }

  static void writeFile(DataInputStream is, String name, int bytes) throws IOException {
    name=is.readUTF();
    System.out.println(name);
    byte buf[] = is.readUTF().getBytes();
    DataOutputStream os = new DataOutputStream(new FileOutputStream(name+".txt"));
    os.write(buf, 0, buf.length);
    os.close();
  }

  static Vector v = new Vector();

  static void readRecord(DataInputStream is, String name) throws IOException {
    Record r = new Record(name);
    v.addElement(r);
    r.localChunkID = is.readInt();
    r.attributes = is.readByte();
    is.read(r.uniqueID);
  }
}

  class Record {
    int localChunkID;
    byte attributes;
    byte uniqueID[];
    String name;

    Record(String name) {
      this.name = name;
      uniqueID = new byte[3];
    }
  }
