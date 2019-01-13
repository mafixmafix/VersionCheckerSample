package tk.pallas.versioncheckersample;

import android.support.annotation.NonNull;

public class ResultModel {
    Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @NonNull
    @Override
    public String toString() {
        return data.toString();
    }

    public static class Data {
        Integer id;
        String first_name;
        String last_name;
        String avatar;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        @NonNull
        @Override
        public String toString() {
            return "id: " + id
                    +"\t first_name: " + first_name
                    +"\t last_name: " + last_name
                    +"\t avatar: " + avatar;
        }
    }
}
