/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import com.ctre.phoenix.motorcontrol.*; // BRIAN cleanup
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.PWMSpeedController;
//import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
//import frc.robot.commands.Capacitor; // BRIAN cleanup

/**
 * Add your docs here.
 */
public class Intake extends SubsystemBase {

  //Intake Constants
  private double intakeSpeed = 0.25;
  private double outputSpeed = -0.25;

  private double magSpeed = 0.5;

  //Motors
  private WPI_TalonSRX intakeMotor = new WPI_TalonSRX(RobotMap.INTAKE_MOTOR);
  private WPI_TalonSRX magMotor1 = new WPI_TalonSRX(RobotMap.MAGAZINE_MOTOR_1);

  //Encoder
  private Encoder magEncoder = new Encoder(RobotMap.MAGAZINE_ENCODER[0], RobotMap.MAGAZINE_ENCODER[1], true);
  /*                    //Yellow and Blue          */

  //Distance Sensors ****Figure out AnalogInput Library                   // 5n/cm
  private AnalogPotentiometer sensor1 = new AnalogPotentiometer(RobotMap.MAGAZINE_SENSOR_1, 1000);
  private AnalogPotentiometer sensor2 = new AnalogPotentiometer(RobotMap.MAGAZINE_SENSOR_2, 1000);
  private AnalogPotentiometer sensor3 = new AnalogPotentiometer(RobotMap.MAGAZINE_SENSOR_3, 1000);
  
   public Intake() {
    //setDefaultCommand(new Capacitor()); // BRIAN moved to robot init
  }

  /* BRIAN- Replaced
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new Capacitor());
  }*/

  //Motor Functions

  public void setIntakeSpeed(double speed){
    intakeMotor.set(speed);
  }

  public void intakeIn(){
    intakeMotor.set(intakeSpeed);
  }

  public void intakeOut(){
    intakeMotor.set(outputSpeed);
  }

  public void stopIntake(){
    intakeMotor.stopMotor();
  }


  public void setMagSpeed(double speed){
    magMotor1.set(speed);
  }

  public void magIn(){
    magMotor1.set(magSpeed);
  }

  public void magOut(){
    magMotor1.set(-magSpeed);
  }

  public void stopMag(){
    magMotor1.stopMotor();
  }
  
  //Mag Encoder
  public int getMagEncoder(){
    return magEncoder.get();
  }

  public void resetMagEncoder(){
    magEncoder.reset();
  }


  //Distance Sensor Functions

  public double getSensor1(){
    
    return sensor1.get();
  }

  
  public double getSensor2(){
    return sensor2.get();
  }

  public double getSensor3(){
    return sensor3.get();
  }
  

}
