import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @ClassName MyRealm
 * @Description TODO
 * @Author anjunshuang
 * @Date 2019/7/11 15:55
 * @Version 1.0
 */
public class MyRealm implements Realm{

    @Override
    public String getName() {
        return "MyRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持UsernamePasswordToken类型的Token
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();  //得到用户名
        String password = new String((char[])token.getCredentials()); //得到密码
        if(!"ajs".equals(username)) {
            throw new UnknownAccountException(); //如果用户名错误
        }
        if(!"ajs".equals(password)) {
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
