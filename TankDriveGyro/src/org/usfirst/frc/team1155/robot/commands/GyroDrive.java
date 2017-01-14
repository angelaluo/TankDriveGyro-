package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;

public class GyroDrive extends SampleRobot {

 private RobotDrive myRobot; 
 double Kp = 0.03;

    public GyroSample() {
        gyro = new AnalogGyro(1); 
        myRobot = new RobotDrive(1,2); 
        myRobot.setExpiration(0.1);
        }

   }
