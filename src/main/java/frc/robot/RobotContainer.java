package frc.robot;

import frc.robot.subsystems.Pneumatics;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.Commands;

public class RobotContainer {
  // private final Pneumatics m_pneumatics = new Pneumatics();

  private final CommandXboxController m_driverController =
      new CommandXboxController(0);  //change port #

  public RobotContainer() {
    configureBindings();
  }


  private void configureBindings() {
  }

  public Command getAutonomousCommand() {
    return Commands.none(); 
  }
}