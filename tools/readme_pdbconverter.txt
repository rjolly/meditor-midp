
This is to convert .txt files to and from PalmOS .pdb databases, allowing for meditor data exchange between a palm device and a computer. To updload text files to the device:
  java -classpath pdbconverter.jar files2pdb *.txt
  pilot-xfer -i meditor-VM00.pdb

To download text files from the device:
  pilot-xfer -f meditor-VM00
  java -classpath pdbconverter.jar pdb2files meditor-VM00.pdb

(pilot-xfer is part of the pilot-link linux package. There are similar means of transfering palm databases on other platforms : refer to the hotsync documentation.)

files2pdb and pdb2files were adapted from the original, general purpose converters by Ken Shirriff ( http://www.righto.com/pilot/pdb.html ), in order to handle meditor's format.

