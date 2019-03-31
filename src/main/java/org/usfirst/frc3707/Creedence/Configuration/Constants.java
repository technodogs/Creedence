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

    public static class PneumaticSystem {
        private static int pcmCANID = 1;

        public static int getPcmCanId() {
            return pcmCANID;
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

            private double offset_CompBot;
            private double offset_PracticeBot;

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

            public void setCompBotOffset(double offset) {
                this.offset_CompBot = offset;
            }

            public void setPracticeBotOffset(double offset) {
                this.offset_PracticeBot = offset;
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

            public double getOffset() {
                if (robotType == RobotType.CompetitionRobot) {
                    return offset_CompBot;
                } else if (robotType == RobotType.PracticeRobot) {
                    return offset_PracticeBot;
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

            // Front Left - Competition Robot
            FrontLeft.setCompBotDrive(19);
            FrontLeft.setCompBotSwerve(18);
            FrontLeft.setCompBotEncoder(0);
            FrontLeft.setCompBotOffset(137);
            // Front Left - Practice Robot
            FrontLeft.setPracticeBotDrive(19);
            FrontLeft.setPracticeBotSwerve(18);
            FrontLeft.setPracticeBotEncoder(0);
            FrontLeft.setPracticeBotOffset(117);

            // Front Right - Competition Robot
            FrontRight.setCompBotDrive(17);
            FrontRight.setCompBotSwerve(16);
            FrontRight.setCompBotEncoder(3);
            FrontRight.setCompBotOffset(235);
            // Front Left - Practice Robot
            FrontRight.setPracticeBotDrive(17);
            FrontRight.setPracticeBotSwerve(16); // 5?
            FrontRight.setPracticeBotEncoder(3);
            FrontRight.setPracticeBotOffset(302);

            // Back Left - Competition Robot
            BackLeft.setCompBotDrive(13);
            BackLeft.setCompBotSwerve(12);
            BackLeft.setCompBotEncoder(1);
            BackLeft.setCompBotOffset(276);
            // Back Left - Competition Robot
            BackLeft.setPracticeBotDrive(13);
            BackLeft.setPracticeBotSwerve(12);
            BackLeft.setPracticeBotEncoder(1);
            BackLeft.setPracticeBotOffset(312);

            // Back Right - Competition Robot
            BackRight.setCompBotDrive(15);
            BackRight.setCompBotSwerve(14);
            BackRight.setCompBotEncoder(2);
            BackRight.setCompBotOffset(271);
            // Back Right - Practice Robot
            BackRight.setPracticeBotDrive(15);
            BackRight.setPracticeBotSwerve(14);
            BackRight.setPracticeBotEncoder(2);
            BackRight.setPracticeBotOffset(278);

        }
    }

    public static class CargoSystem {

        private static int CargoSucc = 6;
        private static int LCargoSpit = 10;
        private static int RCargoSpit = 11;
        private static boolean CargoSuccInverted_CompBot = false;
        private static boolean CargoSuccInverted_PracticeBot = false;

        private static boolean LSpitInverted_CompBot = false;
        private static boolean LSpitInverted_PracticeBot = false;

        private static boolean RSpitInverted_CompBot = false;
        private static boolean RSpitInverted_PracticeBot = false;

        public static int getCargoSucc() {
            return CargoSucc;
        }

        public static int getLCargoSpit() {
            return LCargoSpit;
        }

        public static int getRCargoSpit() {
            return RCargoSpit;
        }

        public static boolean getCargoSuccInverted() {
            if (robotType == RobotType.CompetitionRobot) {
                return CargoSuccInverted_CompBot;
            } else {
                return CargoSuccInverted_PracticeBot;
            }
        }

        public static boolean getLSpitInverted() {
            if (robotType == RobotType.CompetitionRobot) {
                return LSpitInverted_CompBot;
            } else {
                return LSpitInverted_PracticeBot;
            }
        }

        public static boolean getRSpitInverted() {
            if (robotType == RobotType.CompetitionRobot) {
                return RSpitInverted_CompBot;
            } else {
                return RSpitInverted_PracticeBot;
            }
        }
    }

    public static class HatchSystem {
        private static int clawButton1 = 1;
        private static int clawButton2 = 2;

        private static int clampSolenoidForward = 2;
        private static int clampSolenoidReverse = 3;

        private static int thrustSolenoidForward = 0;
        private static int thrustSolenoidReverse = 1;

        public static int getClawButton1() {
            return clawButton1;
        }

        public static int getClawButton2() {
            return clawButton2;
        }

        public static int getClampSolenoidForward() {
            return clampSolenoidForward;
        }

        public static int getClampSolenoidReverse() {
            return clampSolenoidReverse;
        }

        public static int getThrustSolenoidForward() {
            return thrustSolenoidForward;
        }

        public static int getThrustSolenoidReverse() {
            return thrustSolenoidReverse;
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

    public static class LiftSystem {
        private static int pneumaticLiftForward = 4;
        private static int pneumaticLiftReverse = 5;

        public static int getPneumaticLiftForward() {
            return pneumaticLiftForward;
        }

        public static int getPneumaticLiftReverse() {
            return pneumaticLiftReverse;
        }

    }

    // the following should be placed into sub-classes of Constants
    public static int Grabber = 7;
    public static int Winch = 8;
    public static int Elevator = 9;
    public static int lidar1 = 0;

}