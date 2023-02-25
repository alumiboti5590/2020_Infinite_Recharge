/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
//import edu.wpi.first.wpilibj.motorcontrol.Talon; // BRIAN cleanup
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive; // BRIAN cleanup
import frc.robot.RobotMap;
//import frc.robot.commands.TankDrive; // BRIAN cleanup

/**
 * Add your docs here.
 */
public class DriveTrain extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public PWMVictorSPX leftDriveMotor = new PWMVictorSPX(RobotMap.LEFT_DRIVE_MOTOR);
  public PWMVictorSPX rightDriveMotor = new PWMVictorSPX(RobotMap.RIGHT_DRIVE_MOTOR);

  //Encoders
  //** ENCODERS MAY JUST BE DIGITAL INPIUTS */
  private Encoder leftDriveEncoder = new Encoder(RobotMap.LEFT_DRIVE_ENCODER[0], RobotMap.LEFT_DRIVE_ENCODER[1]);
  private Encoder rightDriveEncoder = new Encoder(RobotMap.RIGHT_DRIVE_ENCODER[0], RobotMap.RIGHT_DRIVE_ENCODER[1]);

  public DriveTrain() {
    rightDriveMotor.setInverted(true); // BRIAN setting this as right motor was reveresed somehow
    //setDefaultCommand(new TankDrive()); // BRIAN moved to robot init
  }

  /* BRIAN- Replaced
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new TankDrive());
  }*/


  public int getLeftEncoderValue(){
    return leftDriveEncoder.get();
  }

  public int getRightEncoderValue(){
    return rightDriveEncoder.get();
  }

  public void zeroEncoders(){
    leftDriveEncoder.reset();
    rightDriveEncoder.reset();
  }

  public void moveStraight(double speed){
      //drive.tankDrive(speed, speed);
      leftDriveMotor.set(speed);
      rightDriveMotor.set(speed);
      //rightDriveMotor.set(-speed * 0.9);
      //System.out.println(" " + leftDriveMotor.getSpeed());
  }

 // public void runTest() {
  // TankDrive drive = new TankDrive();
 /*   int i = 0;
    while (i < 1) {

      System.out.println(System.nanoTime());
      drive.testDrive();
      try {
        Thread.sleep(1, 0);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      i++;
     } */
   //  drive.testDrive();
//}




}
