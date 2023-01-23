/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Capacitor extends Command {

  // ** NAME VARIABLES BETTER!!! */

  // Semi-Auto Capacitor Variables
  public static boolean SemiAuto = true;

  public boolean intaking = false;

  private int magInDistanceInches = 7;
  private int magInDistanceRaw = magInDistanceInches * (5000 / 7);
  private boolean movingBall = false;

  // Constants        5n/cm
  private static double ballPresenceDistance1 = 30;
  private static double ballPresenceDistance2 = 90;
  private static double ballPresenceDistance3 = 100;

  // Sensor Values
  private double sensorDistance1;
  private double sensorDistance2;
  private double sensorDistance3;

  private boolean ballSensed = false;

  // Driver Trigger Values
  private double leftTrigger;
  private double rightTrigger;

  // Driver Bumper Values
  private boolean leftBumper, pressedBumper = false;

  public Capacitor() {

    requires(Robot.intake);
  }

  @Override
  protected void initialize() {
    // Driver Trigger Values
    leftTrigger = Robot.oi.getDriverLeftTrigger();
    rightTrigger = Robot.oi.getDriverRightTrigger();

    leftBumper = Robot.oi.getDriverLeftBumper();

    // Sensor Distances
    sensorDistance1 = Robot.intake.getSensor1();
    sensorDistance2 = Robot.intake.getSensor2();
    sensorDistance3 = Robot.intake.getSensor3();
  }

  @Override
  protected void execute() {

    // Driver Triggers
    leftTrigger = Robot.oi.getDriverLeftTrigger();
    rightTrigger = Robot.oi.getDriverRightTrigger();

    leftBumper = Robot.oi.getDriverLeftBumper();

    // Sensor Distances
    sensorDistance1 = Robot.intake.getSensor1();
    sensorDistance2 = Robot.intake.getSensor2();
    sensorDistance3 = Robot.intake.getSensor3();

    // System.out.println(sensorDistance1 + " " + intakeFilled());
    // System.out.println(sensorDistance2 + " " + magStage1Filled());
    // System.out.println(sensorDistance3 + " " + magStage2Filled());
    // System.out.println();
    // System.out.println(Robot.intake.getMagEncoder());

    // ENCODER: 5000 ticks for 7inches(diameter of ball)

    // Semi-Auto Intake/Magazine

    if (SemiAuto) {

      if ((intakeFilled() && !magStage2Filled()) && false) {

        /*if(magStage1Filled()){
          movingBall = true;
        } else {
          Robot.intake.intakeIn();
        }*/

      } else {

        // Manual Intake
        if (leftTrigger != 0) {
          Robot.intake.intakeIn();
        } else if (rightTrigger != 0) {
          Robot.intake.intakeOut();
        } else {
          Robot.intake.stopIntake();
        }

        if (leftBumper) {
          movingBall = true;
        }

        if (intakeFilled() && !ballSensed) {
          movingBall = true;
          ballSensed = true;
        }

        if (!intakeFilled()) {
          ballSensed = false;
        }

        /* if(!pressedBumper && leftBumper){
          movingBall = true;
          pressedBumper = true;
        }

        if(!pressedBumper && !leftBumper){
          movingBall = false;
          Robot.intake.stopMag();
          Robot.intake.resetMagEncoder();
        }

        if(!leftBumper){
          pressedBumper = false;
        }*/

      }

      if (movingBall) {
        // System.out.println((float)(Robot.intake.getMagEncoder() * 5000/7) + " inches");
        if (!movedBallAmount()) {
          Robot.intake.magIn();
        } else {
          Robot.intake.stopMag();
          Robot.intake.resetMagEncoder();
          movingBall = false;
        }
      }

    }
    // end of semi-auto
    else {
      if (leftTrigger != 0) {
        Robot.intake.intakeIn();
        Robot.intake.magIn();
      } else if (rightTrigger != 0) {
        Robot.intake.intakeOut();
        Robot.intake.magOut();
      } else {
        Robot.intake.stopIntake();
        Robot.intake.stopMag();
      }
    }
    // USEABLE//
    /* if(!magStage2Filled()){
      if(intakeFilled()){
          Robot.intake.intakeIn();
          Robot.intake.magIn();

      } else{

         if(magStage1Filled()){
          Robot.intake.stopMag();

         }

          if(leftTrigger!=0){
            Robot.intake.intakeIn();
          }else if(rightTrigger!=0){
            Robot.intake.intakeOut();
          }else{
            Robot.intake.stopIntake();
          }


      }

    }
    //END OF USEABLE//





    //Probably Useless
    /*if(!intakeFilled()){
      if(leftTrigger!=0){
          Robot.intake.setIntakeSpeed(0.5);
      } else if(rightTrigger!=0){
          Robot.intake.setIntakeSpeed(-0.5);
      } else{
          Robot.intake.stopIntake();
     }
          Robot.intake.stopMag();

    } else {
        if(magStage1Filled()){
          Robot.intake.magIn();
        } else {
          Robot.intake.intakeIn();
        }

    }*/

  }
  // END OF EXECUTE

  //
  // Functions for Magazine/Intake Operation
  //

  // Detect Ball Presence
  public boolean intakeFilled() {
    if (sensorDistance1 <= ballPresenceDistance1) {
      return true;
    }
    return false;
  }

  public boolean magStage1Filled() {
    if (sensorDistance2 <= ballPresenceDistance2) {
      return true;
    }
    return false;
  }

  public boolean magStage2Filled() {
    if (sensorDistance3 <= ballPresenceDistance3) {
      return true;
    }
    return false;
  }

  public boolean totalEmpty() {
    if (!intakeFilled() && !magStage1Filled() && !magStage2Filled()) {
      return true;
    } else {
      return false;
    }
  }

  public boolean defaultPosition() {
    if (magStage1Filled() && !intakeFilled()) {
      return true;
    } else {
      return false;
    }
  }

  public boolean movedBallAmount() {
    if (Robot.intake.getMagEncoder() < magInDistanceRaw) {
      // Robot.intake.magIn();
      return false;
    } else {
      // Robot.intake.stopMag();
      // Robot.intake.resetMagEncoder();
      return true;
    }
  }

  //
  // ** MAGAZINE BALL COUNTER (checks when intake changes availability)*/
  //

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {}

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {}
}
