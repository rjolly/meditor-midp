package jscl.editor.micro;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import javax.microedition.rms.*;
import java.io.*;
import jscl.math.*;

public class EditorMIDlet extends MIDlet implements CommandListener, RecordFilter, RecordComparator {
	private static final int MAXSIZE=65536;
	private static final int NAMEMAXSIZE=32;
	private final String copyright;
	private final Command markCommand;
	private final Command expandCommand;
	private final Command factorizeCommand;
	private final Command elementaryCommand;
	private final Command simplifyCommand;
	private final Command newCommand;
	private final Command openCommand;
	private final Command saveCommand;
	private final Command saveAsCommand;
	private final Command deleteCommand;
	private final Command aboutCommand;
	private final Command exitCommand;
	private final Command okCommand;
	private final Command cancelCommand;
	private RecordStore recordStore;
	private Display display;
	private String name;
	private List openList;
	private List deleteList;
	private Form saveForm;
	private TextBox box;
	private boolean marked;
	private int mark;

	public EditorMIDlet() {
		display = Display.getDisplay(this);
		markCommand = new Command(getAppProperty("Command-Mark"), Command.SCREEN, 1);
		expandCommand = new Command(getAppProperty("Command-Expand"), Command.SCREEN, 1);
		factorizeCommand = new Command(getAppProperty("Command-Factorize"), Command.SCREEN, 2);
		elementaryCommand = new Command(getAppProperty("Command-Elementary"), Command.SCREEN, 2);
		simplifyCommand = new Command(getAppProperty("Command-Simplify"), Command.SCREEN, 2);
		newCommand = new Command(getAppProperty("Command-New"), Command.SCREEN, 1);
		openCommand = new Command(getAppProperty("Command-Open"), Command.SCREEN, 3);
		saveCommand = new Command(getAppProperty("Command-Save"), Command.SCREEN, 3);
		saveAsCommand = new Command(getAppProperty("Command-SaveAs"), Command.SCREEN, 3);
		deleteCommand = new Command(getAppProperty("Command-Delete"), Command.SCREEN, 4);
		aboutCommand = new Command(getAppProperty("Command-About"), Command.HELP, 1);
		exitCommand = new Command(getAppProperty("Command-Exit"), Command.EXIT, 1);
		okCommand = new Command(getAppProperty("Command-Ok"), Command.OK, 1);
		cancelCommand = new Command(getAppProperty("Command-Cancel"), Command.CANCEL, 1);
		copyright=readFile(getAppProperty("File-Copyright"));
		openRecordStore();
		box=new TextBox(getAppProperty("Name-Untitled"),"",MAXSIZE,TextField.ANY);
		box.addCommand(markCommand);
		box.addCommand(expandCommand);
		box.addCommand(factorizeCommand);
		box.addCommand(elementaryCommand);
		box.addCommand(simplifyCommand);
		box.addCommand(newCommand);
		box.addCommand(openCommand);
		box.addCommand(saveCommand);
		box.addCommand(saveAsCommand);
		box.addCommand(deleteCommand);
		box.addCommand(aboutCommand);
		box.addCommand(exitCommand);
		box.setCommandListener(this);
	}

	private void openRecordStore() {
		try {
			recordStore = RecordStore.openRecordStore(getAppProperty("MIDlet-Name"), false);
		} catch (RecordStoreNotFoundException e) {
			try {
				recordStore = RecordStore.openRecordStore(getAppProperty("MIDlet-Name"), true);
				name=getAppProperty("Name-QuickRef");
				save(readFile(getAppProperty("File-QuickRef")));
				name=null;
			} catch (RecordStoreException ex) {
				ex.printStackTrace();
			}
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
	}

	private String readFile(String file) {
		InputStream is=getClass().getResourceAsStream(file);
		ByteArrayOutputStream os=new ByteArrayOutputStream();
		try {
			byte b[]=new byte[1024];
			while(true) {
				int n=is.read(b);
				if(n<0) break;
				os.write(b,0,n);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return os.toString();
	}

	private String load() {
		String text="";
		int id = index();
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(recordStore.getRecord(id));
			DataInputStream inputStream = new DataInputStream(bais);
			try {
				String name = inputStream.readUTF();
				text = inputStream.readUTF();
			} catch (EOFException ex) {
				ex.printStackTrace();
			}
		} catch (RecordStoreException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}

	private void delete() {
		int id = index();
		try {
			recordStore.deleteRecord(id);
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
	}

	private void save(String text) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream outputStream = new DataOutputStream(baos);
		try {
			outputStream.writeUTF(name);
			outputStream.writeUTF(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int id = index();
		byte[] b = baos.toByteArray();
		try {
			if(id<recordStore.getNextRecordID()) recordStore.setRecord(id, b, 0, b.length);
			else recordStore.addRecord(b, 0, b.length);
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
	}

	int index() {
		int id = 0;
		try {
			RecordEnumeration re = recordStore.enumerateRecords(this, null, false);
			if(re.hasNextElement()) {
				id = re.nextRecordId();
			} else {
				id = recordStore.getNextRecordID();
			}
			re.destroy();
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
		return id;
	}

	public boolean matches(byte[] candidate) throws IllegalArgumentException {
		if (this.name == null) {
			return false;
		}
		ByteArrayInputStream bais = new ByteArrayInputStream(candidate);
		DataInputStream inputStream = new DataInputStream(bais);
		String name = null;
		try {
			name = inputStream.readUTF();
		} catch (EOFException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (this.name.equals(name));
	}

	public int compare(byte[] rec1, byte[] rec2) {
		ByteArrayInputStream bais1 = new ByteArrayInputStream(rec1);
		DataInputStream inputStream1 = new DataInputStream(bais1);
		ByteArrayInputStream bais2 = new ByteArrayInputStream(rec2);
		DataInputStream inputStream2 = new DataInputStream(bais2);
		String name1 = "";
		String name2 = "";
		try {
			name1 = inputStream1.readUTF();
			name2 = inputStream2.readUTF();
		} catch (EOFException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (name1.compareTo(name2) < 0) {
			return RecordComparator.PRECEDES;
		} else if (name1.compareTo(name2) > 0) {
			return RecordComparator.FOLLOWS;
		} else {
			return RecordComparator.EQUIVALENT;
		}
	}

	protected void startApp() {
		display.setCurrent(box);
	}

	protected  void pauseApp() {}

	protected void destroyApp(boolean unconditional) {
		try {
			recordStore.closeRecordStore();
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
	}

	public void commandAction(Command c, Displayable s) {
		if (s == box) {
			if (c == markCommand) {
				mark=box.getCaretPosition();
				marked=true;
			} else if (c == expandCommand || c == factorizeCommand || c == elementaryCommand || c == simplifyCommand) {
				process(c);
			} else if (c == newCommand) {
				if(modified()) {
					Alert alert=new Alert(getAppProperty("Command-New"),getAppProperty("Alert-Changes"),null,AlertType.CONFIRMATION);
					display.setCurrent(alert);
				} else {
					name=null;
					setTitle();
					box.setString("");
					marked=false;
				}
			} else if (c == openCommand) {
				if(modified()) {
					Alert alert=new Alert(getAppProperty("Command-Open"),getAppProperty("Alert-Changes"),null,AlertType.CONFIRMATION);
					display.setCurrent(alert);
				} else {
					setupOpenList();
					display.setCurrent(openList);
				}
			} else if (c == saveCommand) {
				if(name==null) {
					setupSaveForm();
					display.setCurrent(saveForm);
				} else save(box.getString());
			} else if (c == saveAsCommand) {
				setupSaveForm();
				display.setCurrent(saveForm);
			} else if (c == deleteCommand) {
				setupDeleteList();
				display.setCurrent(deleteList);
			} else if (c == aboutCommand) {
				Alert alert=new Alert(getAppProperty("Title-Copyright"));
				alert.setTimeout(Alert.FOREVER);
				alert.setString(copyright);
				display.setCurrent(alert);
			} else if (c == exitCommand) {
				if(modified()) {
					Alert alert=new Alert(getAppProperty("Command-Exit"),getAppProperty("Alert-Changes"),null,AlertType.CONFIRMATION);
					display.setCurrent(alert);
				} else {
					destroyApp(false);
					notifyDestroyed();
				}
			}
		} else if (s == saveForm) {
			if (c == okCommand) {
				name=((TextField)saveForm.get(0)).getString();
				setTitle();
				save(box.getString());
			}
			display.setCurrent(box);
		} else if (s == openList) {
			if (c == List.SELECT_COMMAND) {
				name=openList.getString(openList.getSelectedIndex());
				setTitle();
				box.setString(load());
				marked=false;
			}
			display.setCurrent(box);
		} else if (s == deleteList) {
			if (c == List.SELECT_COMMAND) {
				String name=this.name;
				this.name=deleteList.getString(deleteList.getSelectedIndex());
				delete();
				this.name=name;
			}
			display.setCurrent(box);
		}
	}

	void process(final Command c) {
		if(!marked) {
			Alert alert=new Alert(c.getLabel(),getAppProperty("Alert-NoMark"),null,AlertType.ERROR);
			display.setCurrent(alert);
		} else {
			final int n=box.getCaretPosition();
			final String text=box.getString().substring(mark,n);
			new Thread() {
				public void run() {
					try {
						Object o[]=(Object[])Expression.userInput.parse(text,new int[1]);
						boolean equal=((Boolean)o[1]).booleanValue();
						Arithmetic in=(Arithmetic)o[0];
						Arithmetic out=process(c,in);
						String s=equal?text+out.toString():out.toString();
						box.delete(mark,n-mark);
						box.insert(s,mark);
						marked=false;
					} catch (Throwable ex) {
						Alert alert=new Alert(c.getLabel(),ex.toString(),null,AlertType.ERROR);
						alert.setTimeout(Alert.FOREVER);
						display.setCurrent(alert,box);
						Debug.reset();
					}
				}
			}.start();
		}
	}

	Arithmetic process(Command c, Arithmetic in) {
		if (c == expandCommand) return in.expand();
		else if (c == factorizeCommand) return in.factorize();
		else if (c == elementaryCommand) return in.elementary();
		else if (c == simplifyCommand) return in.simplify();
		else return null;
	}

	private void setupSaveForm() {
		saveForm=new Form(getAppProperty("Command-SaveAs"),new Item[] {new TextField(getAppProperty("Label-Name"),name==null?"":name,NAMEMAXSIZE,TextField.ANY)});
		saveForm.addCommand(okCommand);
		saveForm.addCommand(cancelCommand);
		saveForm.setCommandListener(this);
	}

	private void setupOpenList() {
		openList=new List(getAppProperty("Command-Open"),List.IMPLICIT);
		openList.addCommand(cancelCommand);
		openList.setCommandListener(this);
		fillList(openList);
	}

	private void setupDeleteList() {
		deleteList=new List(getAppProperty("Command-Delete"),List.IMPLICIT);
		deleteList.addCommand(cancelCommand);
		deleteList.setCommandListener(this);
		fillList(deleteList);
	}

	private void fillList(List list) {
		try {
			RecordEnumeration re = recordStore.enumerateRecords(null, this, false);
			while(re.hasNextElement()) {
				ByteArrayInputStream bais = new ByteArrayInputStream(re.nextRecord());
				DataInputStream inputStream = new DataInputStream(bais);
				try {
					String name = inputStream.readUTF();
					list.append(name,null);
				} catch (EOFException ex) {
					ex.printStackTrace();
				}
			}
			re.destroy();
		} catch (RecordStoreException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setTitle() {
		box.setTitle(name==null?getAppProperty("Name-Untitled"):name);
	}

	private boolean modified() {
		return false;
	}
}
