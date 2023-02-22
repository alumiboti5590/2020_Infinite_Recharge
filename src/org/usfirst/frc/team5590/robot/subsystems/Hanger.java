/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
//import com.ctre.phoenix.motorcontrol.*; // BRIAN cleanup
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
//import frc.robot.commands.Reach; // BRIAN cleanup
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class Hanger extends SubsystemBase {

  //Reach Speed
  private double reachMotorSpeed = -0.50;
  private double winchMotorSpeed = 0.8;

  private WPI_TalonSRX reachMotor = new WPI_TalonSRX(RobotMap.REACH_MOTOR);
  private WPI_TalonSRX winchMotor = new WPI_TalonSRX(RobotMap.WINCH_MOTOR);
  private DoubleSolenoid reachSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RobotMap.REACH_SOLENOID[0], RobotMap.REACH_SOLENOID[1]); // BRIAN - Not sure about module type

  public Hanger() {
    //setDefaultCommand(new Reach()); // BRIAN moved to robot init
  }

  /* BRIAN- Replaced
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new Reach());
  }*/

  //Winch Motor
  public void retractWinch(){
    winchMotor.set(winchMotorSpeed);
  }

  //added by matt
  public void unwindWinch(){
    winchMotor.set(-winchMotorSpeed);
  }

  public void stopWinch(){
    winchMotor.stopMotor();
  }

  public void extendWinch(){
    winchMotor.set(-winchMotorSpeed);
  }

  //Reach Motors
  public void extendReach(){
    reachMotor.set(reachMotorSpeed);
  }

  public void retractReach(){
    reachMotor.set(-reachMotorSpeed);
  }

  public void stopReachMotor(){
    reachMotor.stopMotor();
  }

  //Reach Solenoid
  public void raiseReach(){
    reachSolenoid.set(DoubleSolenoid.Value.kForward);
  }
  public void lowerReach(){
    reachSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void stopReachSolenoid(){
    reachSolenoid.set(DoubleSolenoid.Value.kOff);
  }
  

}
