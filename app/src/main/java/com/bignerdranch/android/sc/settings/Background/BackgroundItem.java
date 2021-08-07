package com.bignerdranch.android.sc.settings.Background;
public class BackgroundItem {


    public class Background {


        /**
         * b_1 : 0
         * b_2 : 0
         * b_3 : 0
         * b_4 : 0
         * b_5 : 0
         */

        private int b_1;
        private int b_2;
        private int b_3;
        private int b_4;
        private int b_5;

        public int getB_1() {
            return b_1;
        }

        public void setB_1(int b_1) {
            this.b_1 = b_1;
        }

        public int getB_2() {
            return b_2;
        }

        public void setB_2(int b_2) {
            this.b_2 = b_2;
        }

        public int getB_3() {
            return b_3;
        }

        public void setB_3(int b_3) {
            this.b_3 = b_3;
        }

        public int getB_4() {
            return b_4;
        }

        public void setB_4(int b_4) {
            this.b_4 = b_4;
        }

        public int getB_5() {
            return b_5;
        }

        public void setB_5(int b_5) {
            this.b_5 = b_5;
        }


        public int get(int num) {
            switch (num) {
                case 1:
                    return getB_1();
                case 2:
                    return getB_2();
                case 3:
                    return getB_3();
                case 4:
                    return getB_4();
                case 5:
                    return getB_5();
            }
            return 0;
        }
    }

    public class Buy{

        /**
         * backdrop_id : 0
         */

        private int backdrop_id;

        public int getBackdrop_id() {
            return backdrop_id;
        }

        public void setBackdrop_id(int backdrop_id) {
            this.backdrop_id = backdrop_id;
        }
    }
}