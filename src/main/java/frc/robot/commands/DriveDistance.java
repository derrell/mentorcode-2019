/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Config;
import frc.robot.subsystems.DriveTrainSubsystem;

public class DriveDistance extends Command
{
  private DriveTrainSubsystem driveTrainSubsystem;
  private DifferentialDrive drive;
  private double startPosition;

  public DriveDistance()
  {
    Config config = Config.getInstance();
    config.setDefaultDouble("command:drive:speed", 0.4);
    config.setDefaultInt("command:drive:distance:inches", 24);

    driveTrainSubsystem = DriveTrainSubsystem.getInstance(); 
    drive = driveTrainSubsystem.getDrive();
    requires(driveTrainSubsystem);

    SmartDashboard.putString("state", "constructor");
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize()
  {
    SmartDashboard.putString("state", "initialize");
    startPosition = driveTrainSubsystem.getEncoderFrontLeft();
    SmartDashboard.putNumber("start encoder value", startPosition);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute()
  {
    double speed;
    Config config = Config.getInstance();
    
    SmartDashboard.putString("state", "execute");
    speed = config.getDouble("command:drive:speed");
    drive.arcadeDrive(speed, 0.0);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished()
  {
    double distance;
    double rotations;
    double endPosition;
    double encoderValue = driveTrainSubsystem.getEncoderFrontLeft();
    Config config = Config.getInstance();
    
    SmartDashboard.putString("state", "isFinished");
    distance = config.getInt("command:drive:distance:inches");

    // Convert distance to rotations. We recalculate this each time to allow
    // for the values to be changed while we're running.
    // Gearbox yields 7.31 motor revolutions per motor output revolution
    // Robot has 6" wheels
    rotations = (distance * 7.31) / (6.0 * Math.PI);

    endPosition = startPosition + rotations;
    SmartDashboard.putNumber("startPosition", startPosition);
    SmartDashboard.putNumber("distance", distance);
    SmartDashboard.putNumber("rotations", rotations);
    SmartDashboard.putNumber("endPosition", endPosition);
    SmartDashboard.putNumber("encoderValue", encoderValue);
    return encoderValue > endPosition;
  }

  // Called once after isFinished returns true
  @Override
  protected void end()
  {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted()
  {
  }
}
