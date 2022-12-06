package namoo.yorizori.receipeProcedure.dto;

/*
 * 사용자 정보 저장을 위한 자바 빈
 */
public class ReciepeProcedure {
	private int receipeId;
	private int seqNum;
	private String[] procedure;
	
	
	public ReciepeProcedure() {}



	public ReciepeProcedure(int receipeId, int seqNum, String[] procedure) {
		super();
		this.receipeId = receipeId;
		this.seqNum = seqNum;
		this.procedure = procedure;
	}


	public int getSeqNum() {
		return seqNum;
	}



	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
	}


	public String[] getProcedure() {
		return procedure;
	}



	public void setProcedure(String[] procedure) {
		this.procedure = procedure;
	}



	public int getReceipeId() {
		return receipeId;
	}



	public void setReceipeId(int receipeId) {
		this.receipeId = receipeId;
	}



	@Override
	public String toString() {
		String str = " [ReciepeProcedure] "+"receipeId: "+receipeId+" seqNum: "+seqNum+" procedure: "+procedure;
		return str;		
	}
	
	
}
