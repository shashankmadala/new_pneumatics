package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;

public class Pneumatics extends SubsystemBase {
    private final DoubleSolenoid pitchSolenoid;
    // private final WPI_TalonFX motor;
    private final Joystick joystick;
    
    private boolean isExtended = false;
    private double currentSpeed = 0.0;
    private final double SPEED_INCREMENT = 0.1;  //change incrementation if needed
    private final double MAX_SPEED = 1.0;
    private final double MIN_SPEED = -1.0;
    
    public Pneumatics() {
        pitchSolenoid = new DoubleSolenoid(
            PneumaticsModuleType.CTREPCM,
            1, 1
        );
        
//change talon id
        // motor = new WPI_TalonFX(0);
        // motor.configFactoryDefault();
        // motor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
        
        joystick = new Joystick(2);
        
        pitchSolenoid.set(kReverse);
        // motor.set(ControlMode.PercentOutput, 0);
    }
    
//increase motor
    public void increaseSpeed() {
        currentSpeed = Math.min(currentSpeed + SPEED_INCREMENT, MAX_SPEED);
        // motor.set(ControlMode.PercentOutput, currentSpeed);
        SmartDashboard.putNumber("Motor Speed", currentSpeed);
    }
    
//decrease motor
    public void decreaseSpeed() {
        currentSpeed = Math.max(currentSpeed - SPEED_INCREMENT, MIN_SPEED);
        // motor.set(ControlMode.PercentOutput, currentSpeed);
        SmartDashboard.putNumber("Motor Speed", currentSpeed);
    }
    
    @Override
    public void periodic() {
        System.out.println("I am jere");
        //change button
        if (joystick.getRawButtonPressed(1)) {
            pitchSolenoid.toggle();
            isExtended = true;
            SmartDashboard.putString("Piston State", "Extended");
        }
        // change button
        else if (joystick.getRawButtonPressed(2)) {
            isExtended = false;
            SmartDashboard.putString("Piston State", "Retracted");
        }

        // change speed button
        if (joystick.getRawButtonPressed(6)) {
            increaseSpeed();
        }
        // change speed button
        else if (joystick.getRawButtonPressed(5)) {
            decreaseSpeed();
        }
        

        SmartDashboard.putBoolean("Piston Extended", isExtended);
        SmartDashboard.putNumber("Current Speed", currentSpeed);
    }
    
//stop
    public void stop() {
        // motor.set(ControlMode.PercentOutput, 0);
        currentSpeed = 0;
        pitchSolenoid.set(kReverse);
        isExtended = false;
    }
}