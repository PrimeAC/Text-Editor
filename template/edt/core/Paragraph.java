package edt.core;

public class Paragraph {
	
	private String _text;

	public void setText(String text ) {
		_text = text;
	}

	public String getContent() {
		return _text;
	}

	public int getSize() {
		return _text.length();
	}
}