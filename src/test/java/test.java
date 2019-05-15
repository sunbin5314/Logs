import com.hzxy.robot.dao.RobotMapper;
import com.hzxy.robot.entity.RobotInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class test {

    @Autowired
    private RobotMapper robotMapper;

    @Test
    public  void test() {
//        String hashAlgorithmName = "MD5";
//        String credentials = "123456";
//        int hashIterations = 1024;
//        ByteSource credentialsSalt = ByteSource.Util.bytes("2w@W");
//        Object obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
//        System.out.println(obj);

        RobotInfo robot = new RobotInfo();
        robot.setName("7号");
        robot.setPosition("天津西青");
        robot.setType("导诉");
        robot.setDescription("添加机器7号");
        robotMapper.addRobot(robot);

    }
}
