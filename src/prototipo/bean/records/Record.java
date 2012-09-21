package prototipo.bean.records;

public class Record implements Comparable<Record>{

	private String jogador;
	private int clicks;
	private long tempo;
	
	public Record(String jogador, int clicks, long tempo) {
		super();
		this.jogador = jogador;
		this.clicks = clicks;
		this.tempo = tempo;
	}

	public String getJogador() {
		return jogador;
	}

	public int getClicks() {
		return clicks;
	}

	public long getTempo() {
		return tempo;
	}

	@Override
	public int compareTo(Record another) {

		if(this.clicks != another.clicks)
			return (this.clicks > another.clicks ? 1 : -1);
		else if(this.tempo != another.tempo)
			return (this.tempo > another.tempo ? 1 : -1);
		return 0;
	}
	
	@Override
	public String toString() {
		return String.format("jogador:%s - clicks:%d - tempo:%d", getJogador(), getClicks(), getTempo());
	}
}
