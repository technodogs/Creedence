package org.usfirst.frc3707.Creedence.Configuration;

public class Constants {

    public enum RobotType {
        CompetitionRobot, PracticeRobot
    }

    // Default to Competition Robot configuration
    public static RobotType robotType = RobotType.CompetitionRobot;

    // public static void setRobotType(RobotType newRobotType) {
    // robotType = newRobotType;
    // }

    public static class HumanInputs {
        public static class DriverInputs {

        }

        public static class OperatorInputs {

        }

    }

    public static class DriveSystem {


        public class DriveModule {
            private int drive_CompBot;
            private int swerve_CompBot;
            private int encoder_CompBot;

            private int drive_PracticeBot;
            private int swerve_PracticeBot;
            private int encoder_PracticeBot;

            public void setCompBotDrive(int drive) {
                this.drive_CompBot = drive;
            }

            public void setCompBotSwerve(int swerve) {
                this.swerve_CompBot = swerve;
            }

            public void setCompBotEncoder(int encoder) {
                this.encoder_CompBot = encoder;
            }

            public void setPracticeBotDrive(int drive) {
                this.drive_PracticeBot = drive;
            }

            public void setPracticeBotSwerve(int swerve) {
                this.swerve_PracticeBot = swerve;
            }

            public void setPracticeBotEncoder(int encoder) {
                this.encoder_PracticeBot = encoder;
            }

            public int getDrive() {
                if (robotType == RobotType.CompetitionRobot) {
                    return drive_CompBot;
                } else if (robotType == RobotType.PracticeRobot) {
                    return drive_PracticeBot;
                } else {
                    return -1;
                }
            }

            public int getSwerve() {
                if (robotType == RobotType.CompetitionRobot) {
                    return swerve_CompBot;
                } else if (robotType == RobotType.PracticeRobot) {
                    return swerve_PracticeBot;
                } else {
                    return -1;
                }
            }

            public int getEncoder() {
                if (robotType == RobotType.CompetitionRobot) {
                    return encoder_CompBot;
                } else if (robotType == RobotType.PracticeRobot) {
                    return encoder_PracticeBot;
                } else {
                    return -1;
                }
            }

        }

        public static DriveModule FrontLeft;
        public static DriveModule FrontRight;
        public static DriveModule BackLeft;
        public static DriveModule BackRight;

        static {
            FrontLeft.setCompBotDrive(19);
            FrontLeft.setCompBotSwerve(18);
            FrontLeft.setCompBotEncoder(0);
            FrontLeft.setPracticeBotDrive(19);
            FrontLeft.setPracticeBotSwerve(18);
            FrontLeft.setPracticeBotEncoder(0);

            FrontRight.setCompBotDrive(17);
            FrontRight.setCompBotSwerve(16);
            FrontRight.setCompBotEncoder(3);
            FrontRight.setPracticeBotDrive(17);
            FrontRight.setPracticeBotSwerve(16); // 5?
            FrontRight.setPracticeBotEncoder(3);

            BackLeft.setCompBotDrive(13);
            BackLeft.setCompBotSwerve(12);
            BackLeft.setCompBotEncoder(1);
            BackLeft.setPracticeBotDrive(13);
            BackLeft.setPracticeBotSwerve(12);
            BackLeft.setPracticeBotEncoder(1);

            BackRight.setCompBotDrive(15);
            BackRight.setCompBotSwerve(14);
            BackRight.setCompBotEncoder(2);
            BackRight.setPracticeBotDrive(15);
            BackRight.setPracticeBotSwerve(14);
            BackRight.setPracticeBotEncoder(2);

        }
    }

    public static class CargoSystem {

        private static int CargoSucc = 6;
        private static int LCargoSpit = 10;
        private static int RCargoSpit = 11;

        public static int getCargoSucc() {
            return CargoSucc;
        }

        public static int getLCargoSpit() {
            return LCargoSpit;
        }

        public static int getRCargoSpit() {
            return RCargoSpit;
        }
    }

    public static class HatchSystem {
        private static int clawButton1 = 1;
        private static int clawButton2 = 2;

        public static int getClawButton1() {
            return clawButton1;
        }

        public static int getClawButton2() {
            return clawButton2;
        }
    }

    public static class ClimbSystem {
        private static int climbBar = 8;
        private static int climbGrab = 7;
        private static int climbPullForward = 4;

        public static int getClimbBar() {
            return climbBar;
        }

        public static int getClimbGrab() {
            return climbGrab;
        }

        public static int getClimbPullForward() {
            return climbPullForward;
        }
    }

    // the following should be placed into sub-classes of Constants
    public static int Grabber = 7;
    public static int Winch = 8;
    public static int Elevator = 9;
    public static int lidar1 = 0;

}