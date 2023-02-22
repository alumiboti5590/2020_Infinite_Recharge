/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

// import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;
//import com.ctre.phoenix.motorcontrol.*; // BRIAN cleanup
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
//import frc.robot.commands.Shoot; // BRIAN cleanup
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class Shooter extends SubsystemBase {

  private WPI_TalonSRX leftShootMotor = new WPI_TalonSRX(RobotMap.LEFT_SHOOT_MOTOR);
  //private WPI_TalonSRX rightShootMotor = new WPI_TalonSRX(RobotMap.RIGHT_SHOOT_MOTOR);

  // private Solenoid shootSolenoid = new Solenoid(RobotMap.SHOOT_SOLENOID);
  private DoubleSolenoid shootSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RobotMap.SHOOT_SOLENOID[0], RobotMap.SHOOT_SOLENOID[1]); // BRIAN - Not sure about module type
  private static double motorSpeed = .70;

  public Shooter() {
    //setDefaultCommand(new Shoot()); // BRIAN moved to robot init
  }

  /* BRIAN- Replaced
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new Shoot());
  }*/

  public void shootOut(){
    leftShootMotor.set(-motorSpeed);
    //rightShootMotor.setSpeed(-motorSpeed);
    
  }

  public void shootIn(){
    leftShootMotor.set(motorSpeed);
    //rightShootMotor.setSpeed(motorSpeed);
  }

  public void stopShoot(){
    leftShootMotor.stopMotor();
    //rightShootMotor.setSpeed(0.0);
  }

  public void raiseShoot(){
    shootSolenoid.set(DoubleSolenoid.Value.kForward);

  }
  public void lowerShoot(){
    shootSolenoid.set(DoubleSolenoid.Value.kReverse);
    
  }

}
