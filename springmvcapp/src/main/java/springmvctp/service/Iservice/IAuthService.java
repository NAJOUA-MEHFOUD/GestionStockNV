package springmvctp.service.Iservice;


public interface IAuthService {
    boolean isValidCredentials(String username, String password);
}
