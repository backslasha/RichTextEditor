package models;

import java.io.File;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractListModel;
import javax.swing.JList;

import bean.Note;


@SuppressWarnings("rawtypes")
public class JListAdapter extends AbstractListModel {
	private static final long serialVersionUID = 1L;
	private String workplace = "";
	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}

	public String getWorkplace() {
		return workplace;
	}

	private Vector<Note> mNotes;
	private JList<?> mJList; 
	
	public void addNote(Note note){
		if(!isAlreadyExist(note)){
			mNotes.add(note);
		}
	}

	private boolean isAlreadyExist(Note note) {
		for(Note n :mNotes){
			if(n.getTitle().equals(note.getTitle())){
				return true;
			}
		}
		return false;
	}

	public JListAdapter(String workplace) {
		this.workplace = workplace;
		this.mNotes = new Vector<Note>();
		
		File d = new File(workplace);
		if (d.exists() && d.isDirectory()) {
			File[] files = d.listFiles();
			Note note;
			for (int i = 0; i < files.length; i++) {
				Pattern pattern = Pattern.compile(".+.(yhb|txt|java)");
				Matcher matcher = pattern.matcher(files[i].getName());
				if(matcher.matches()){
					note = new Note();
					note.setFilePath(files[i].getAbsolutePath());
					note.setTitle(files[i].getName());
					note.setFile(new File(files[i].getAbsolutePath()));
					mNotes.add(note);
				}
				
			}
		}
	}

	
	public Object getElementAt(int arg0) {
		return mNotes.get(arg0);
	}

	public int getSize() {
		return mNotes.size();
	}
	

}
