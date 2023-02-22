/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

//import javax.xml.namespace.QName; // BRIAN cleanup

//import frc.robot.OI;
//import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class TankDrive extends CommandBase {

  //private DriveTrain driveTrain = Robot.driveTrain; // BRIAN change to passing in subsystem
  private DriveTrain m_subsystem;

  //DriveTrain Encoder Values
  private int leftEncoder;
  private int rightEncoder;

  private double leftSpeedMultiplier = 1.0;
  private double rightSpeedMultiplier = 1.0;


  //DifferentialDrive allows a simple, built in tank drive. Uses drive Train Motors
  //private DifferentialDrive robotDrive = new DifferentialDrive(Robot.driveTrain.leftDriveMotor, Robot.driveTrain.rightDriveMotor);
  DifferentialDrive robotDrive = new DifferentialDrive(Robot.driveTrain.leftDriveMotor, Robot.driveTrain.rightDriveMotor);

  //Xbox Driver Sticks (Y-axis)
  private double leftDriverStick;
  private double rightDriverStick;

  public TankDrive(DriveTrain subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_subsystem = subsystem;
    addRequirements(m_subsystem);
 
    //requires(Robot.driveTrain); // BRIAN - TODO
  }


  @Override
  public void initialize() {
      //Robot.driveTrain.talonMotor1.setSpeed(0);

  }

  
  @Override
  public void execute() {

    //UpdateEncoders
    leftEncoder = Robot.driveTrain.getLeftEncoderValue();
    rightEncoder = Robot.driveTrain.getRightEncoderValue();
    //System.out.println(leftEncoder + ", " + rightEncoder);

    //Update Stick position
    leftDriverStick = Robot.oi.getDriverLeftStick();
    rightDriverStick = Robot.oi.getDriverRightStick();
    leftDriverStick = (Math.abs(leftDriverStick) >= 0.05) ? leftDriverStick : 0.0;
    rightDriverStick = (Math.abs(rightDriverStick) >= 0.05) ? rightDriverStick : 0.0;
    
    //tank drive
    robotDrive.tankDrive(leftDriverStick * leftSpeedMultiplier, rightDriverStick * rightSpeedMultiplier);

    //get encoder distance


    //System.out.println(leftDriverStick + ", " + rightDriverStick);

  }
/* BRIAN - TODO
  @Override
  protected boolean isFinished() {
    return false;
  }

  
  @Override
  protected void end() {
    robotDrive.tankDrive(0.0, 0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    System.out.println("TankDrive interrupted");
    robotDrive.tankDrive(0, 0);
  } */

  public void testDrive(){
    //robotDrive.tankDrive(0.75, 0.75);
  }
}
