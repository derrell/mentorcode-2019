/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Config;
import frc.robot.commands.DriveDefault;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class DriveTrainSubsystem extends Subsystem 
{
  private double speed = 0.0;
  private CANSparkMax sparkFrontLeft;
  private CANSparkMax sparkFrontRight;
  private CANSparkMax sparkRearLeft;
  private CANSparkMax sparkRearRight;
  private DifferentialDrive drive;
  private CANEncoder encoderFrontLeft;
  private CANEncoder encoderFrontRight;
  private CANEncoder encoderRearLeft;
  private CANEncoder encoderRearRight;
  private static DriveTrainSubsystem singleton = null;

  private DriveTrainSubsystem() 
  {
    super("DriveTrainSubsystem");

    int canFrontLeft;
    int canRearLeft;
    int canFrontRight;
    int canRearRight;
    Config config;
    SpeedControllerGroup scgLeft;
    SpeedControllerGroup scgRight;
    

    // Get a reference to the configuration
    config = Config.getInstance();

    // Set the default configuration for this subsystem
    config.setDefaultInt("drivetrain:can:motor:frontleft", 3);
    config.setDefaultInt("drivetrain:can:motor:frontright", 1);
    config.setDefaultInt("drivetrain:can:motor:rearleft", 4);
    config.setDefaultInt("drivetrain:can:motor:rearright", 2);
    config.setDefaultString("drivetrain:drivetype", "arcade");

    // Retrieve the canbus motor controller values
    canFrontLeft = config.getInt("drivetrain:can:motor:frontleft");
    canFrontRight = config.getInt("drivetrain:can:motor:frontright");
    canRearLeft = config.getInt("drivetrain:can:motor:rearleft");
    canRearRight = config.getInt("drivetrain:can:motor:rearright");

    // Instantiate the motor controllers
    sparkFrontLeft = new CANSparkMax(canFrontLeft, MotorType.kBrushless);
    sparkFrontRight = new CANSparkMax(canFrontRight, MotorType.kBrushless);
    sparkRearLeft = new CANSparkMax(canRearLeft, MotorType.kBrushless);
    sparkRearRight = new CANSparkMax(canRearRight, MotorType.kBrushless);

    // For sanity sake, set motor controllers back to their difault values
    sparkFrontLeft.restoreFactoryDefaults();
    sparkFrontRight.restoreFactoryDefaults();
    sparkRearLeft.restoreFactoryDefaults();
    sparkRearRight.restoreFactoryDefaults();

    // Left and right motors get their own unique speed controller groups, so they oeprate as a pair
    scgLeft = new SpeedControllerGroup(sparkFrontLeft, sparkRearLeft);
    scgRight = new SpeedControllerGroup(sparkFrontRight, sparkRearRight);

    // Get access to each motor's encoder
    encoderFrontLeft = sparkFrontLeft.getEncoder();
    encoderFrontRight = sparkFrontRight.getEncoder();
    encoderRearLeft = sparkRearLeft.getEncoder();
    encoderRearRight = sparkRearRight.getEncoder();

    // Get the high-level abstraction that provides us with various driving capabilities
    drive = new DifferentialDrive(scgLeft, scgRight);
  }

  static public DriveTrainSubsystem getInstance()
  {
    if (DriveTrainSubsystem.singleton == null)
    {
      DriveTrainSubsystem.singleton = new DriveTrainSubsystem();
    }

    return DriveTrainSubsystem.singleton;
  }

  public DifferentialDrive getDrive()
  {
    return drive;
  }

  public void drive(double speed, double distance)
  {
    // Clamp the speed to legal values
    if (speed < -1.0) speed = -1.0;
    if (speed > 1.0)  speed = 1.0;

    // Save the speed
    this.speed = speed;
 }

 public double getEncoderFrontLeft()
 {
   return encoderFrontLeft.getPosition();
 }

 public double getEncoderFrontRight()
 {
   return encoderFrontRight.getPosition();
 }

 public double getEncoderRearLeft()
 {
   return encoderRearLeft.getPosition();
 }

 public double getEncoderRearRight()
 {
   return encoderRearRight.getPosition();
 }

public void periodic()
  {
    double  currentEncoder;

    // Drive straight ahead (forward or backwards) at the specified speed.
    drive.arcadeDrive(speed, 0.0);

    currentEncoder = this.getEncoderFrontLeft();
    SmartDashboard.putNumber("current encoder value", currentEncoder);

  }

  @Override
  public void initDefaultCommand() 
  {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveDefault());
  }
}

