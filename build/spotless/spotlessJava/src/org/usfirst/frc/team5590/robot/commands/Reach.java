/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Reach extends Command {
  // added by matt
  private boolean backButton;
  private boolean leftBumper, rightBumper;
  private boolean xButtonDown, xButtonUp;
  private boolean yButtonDown;
  private boolean releasedXButton = true;

  private boolean reachActivated = false;

  public Reach() {
    requires(Robot.hanger);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // Robot.hanger.stopReachSolenoid();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    xButtonDown = Robot.oi.isAssistXDown();
    xButtonUp = Robot.oi.isAssistXReleased();
    yButtonDown = Robot.oi.isAssistYDown();
    leftBumper = Robot.oi.getAssistLeftBumper();
    rightBumper = Robot.oi.getAssistRightBumper();
    backButton = Robot.oi.isBackButtonDown();

    // ActivateReach
    if (xButtonUp) {
      releasedXButton = true;
    }

    if (xButtonDown && releasedXButton) {
      reachActivated = !reachActivated;
      releasedXButton = false;
    }

    if (reachActivated) {
      Robot.hanger.raiseReach();

      if (leftBumper) {
        Robot.hanger.extendReach();
      } else if (rightBumper) {
        Robot.hanger.retractReach();
      } else {
        Robot.hanger.stopReachMotor();
      }

    } else if (xButtonUp) {
      Robot.hanger.stopReachSolenoid();
    } else {
      Robot.hanger.lowerReach(); // fixed by James
    }
    if (yButtonDown) {
      Robot.hanger.retractWinch();
    } else if (backButton) {
      Robot.hanger.unwindWinch();
    } else {
      Robot.hanger.stopWinch();
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
    Robot.hanger.stopReachSolenoid();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {}
}
