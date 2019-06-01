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
  private double endRotations;
  private DriveTrainSubsystem driveTrainSubsystem;
  private DifferentialDrive drive;
  private Config config;
 
  public DriveDistance()
  {
    config = Config.getInstance();

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
    double moveRotations;
    double distance;
    double startRotations;
   
    SmartDashboard.putString("state", "initialize");
    startRotations = driveTrainSubsystem.getEncoderFrontLeft();
    SmartDashboard.putNumber("start encoder value", startRotations);

    distance = config.getInt("command:drive:distance:inches");

    // The calculation below, for desired rotations, is based on the theoretical
    // math based on gear ratio and wheel diameter. Something about that math is
    // off by a factor of 1/30. Rather than kludging the values in the math, we'll
    // keep it pure, and adjust the distance here to avoid the overshoot that
    // would otherwise occur.
    distance *= 29.0 / 30.0;

    // Convert distance to rotations. We recalculate this each time to allow
    // for the values to be changed while we're running.
    // Gearbox yields 7.31 motor revolutions per motor output revolution
    // Robot has 6" wheels
    moveRotations = (distance * 7.31) / (6.0 * Math.PI);

    // Calculate the ending encoder value based on where we started and how far
    // we want to go.
    endRotations = startRotations + moveRotations;

    SmartDashboard.putNumber("startPosition", startRotations);
    SmartDashboard.putNumber("distance", distance);
    SmartDashboard.putNumber("rotations", moveRotations);
    SmartDashboard.putNumber("endPosition", endRotations);
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
    double error;
    double speed;
    double encoderValue;
    Config config = Config.getInstance();
    
    SmartDashboard.putString("state", "isFinished");

    // Get the desired normal speed used when we re "far away" from target
    speed = config.getDouble("command:drive:speed");
    
    // Retrieve the current rotation value
    encoderValue = driveTrainSubsystem.getEncoderFrontLeft();

    // Calculate the error between where we want to be and where we are
    error = Math.abs(endRotations - encoderValue);
    
    // If we're getting close, slow us down proportionally to how close we are
    if (error < 4.0)
    {
      speed = (error / 4.0) * speed;
      if (speed < 0.25) speed = 0.25;
      drive.arcadeDrive(speed, 0.0);
    }

    SmartDashboard.putNumber("error", error);
    SmartDashboard.putNumber("speed", speed);
    SmartDashboard.putNumber("encoderValue", encoderValue);

    // We're finished if our current rotations (encoderValue) has exceeded the
    // desired ending rotations value that had been calculated from the distance
    // to be travelled
    return encoderValue > endRotations;
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
