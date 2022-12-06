package namoo.yorizori.common.web;

/**
 * 사용자 정의 예외 런타임 클래스
 * @author LEE KYUHEON
 */
public class YzRuntimeException extends RuntimeException {
	
	private String rediectUrl;

	public YzRuntimeException() {}

	public YzRuntimeException(String message) {
		super(message);
	}

	public YzRuntimeException(String message, String rediectUrl) {
		super(message);
		this.rediectUrl = rediectUrl;
	}

	public String getRedirectUrl() {
		return rediectUrl;
	}

	public void setRediectUrl(String rediectUrl) {
		this.rediectUrl = rediectUrl;
	}
}

