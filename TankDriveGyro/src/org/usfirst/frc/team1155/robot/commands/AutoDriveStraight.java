import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.DriveTrain;

public class AutoDriveStraight extends Command {

    private ADXRS450_Gyro gyro;
    private final double Kp = 0.03;

    private static Joystick leftJoy, rightJoy;

    public AutoDriveStraight() {
		requires(Robot.driveSubsystem);
		leftJoy = Hardware.INSTANCE.leftJoystick;
		rightJoy = Hardware.INSTANCE.rightJoystick;
    }

    @Override
    protected void initialize() {}

    @Override
    protected void execute() {
    	drive.driveCustom(leftJoy.getY(), rightJoy.getY());
    	if (gyro.getAngle() != 0){
    		double angle = gyro.getAngle();
        drive.driveCustom((-angle * (Kp * 0.003)) + (.15), (-angle * (Kp * 0.003)) + (-.15)); // drive towards heading 0
        Timer.delay(0.004);
        }

    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
        drive.driveCustom(0, 0);
    }

    @Override
    protected void interrupted() {
        drive.driveCustom(0, 0);
    }

}
