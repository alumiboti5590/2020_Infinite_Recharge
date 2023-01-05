/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Shoot extends Command {

  //Assistant Controller Triggers
  private double leftTrigger;
  private double rightTrigger;
  private boolean AButton, BButton;

  public Shoot() {
    requires(Robot.shooter);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.shooter.lowerShoot();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Assistant Triggers
    leftTrigger = Robot.oi.getAssistLeftTrigger();
    rightTrigger = Robot.oi.getAssistRightTrigger();
    AButton = Robot.oi.isAssistADown();
    BButton = Robot.oi.isAssistBDown();

    //Shooting
    if(leftTrigger!=0){
      Robot.shooter.shootOut();

    }else if(rightTrigger!=0){
  
    }else{
      Robot.shooter.stopShoot();
    }

    //Height of Shooter
    if(AButton){
      Robot.shooter.raiseShoot();
      //System.out.println("up");
    }else if(BButton){
      Robot.shooter.lowerShoot();
      //System.out.println("down");
    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.shooter.lowerShoot();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
