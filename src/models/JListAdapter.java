package models;

import java.io.File;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractListModel;
import javax.swing.JList;

import bean.Note;


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
	private boolean shouldReturnObject = false;
	private JList<?> mJList; 
	
	public void setReturnObject(){
		shouldReturnObject = true;
	}
	
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
				Pattern pattern = Pattern.compile(".+.txt");
				Matcher matcher = pattern.matcher(files[i].getName());
				if(matcher.matches()){
					note = new Note();
					note.setFilePath(files[i].getAbsolutePath());
					note.setTitle(files[i].getName());
					note.setFile(new File(files[i].getAbsolutePath()));
					mNotes.add(note);
					System.out.println(""+files[i].getName());
				}
				
			}
		}
	}

	public Object getElementAt(int arg0) {
		if (shouldReturnObject) {
			shouldReturnObject = false;
			return mNotes.get(arg0);
		}
		return (arg0+1)+". "+mNotes.get(arg0).getTitle();
	}

	public int getSize() {
		return mNotes.size();
	}

	public void notifyDataSetChange() {
		File d = new File(workplace);
		if (d.exists() && d.isDirectory()) {
			File[] files = d.listFiles();
			Note note;
			for (int i = 0; i < files.length; i++) {
				note = new Note();
				note.setFilePath(files[i].getAbsolutePath());
				note.setTitle(files[i].getName());
				note.setFile(new File(files[i].getAbsolutePath()));
				mNotes.add(note);
			}
		}
		mJList.validate();
		System.out.println("DataSetChange");
	}

}
