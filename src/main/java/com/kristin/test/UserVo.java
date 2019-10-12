package com.kristin.test;

public class UserVo {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public static UserDTO convert(UserVo userVo) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(userVo.getName());
        userDTO.setPassword(userVo.getPassword());
        return userDTO;
    }

    public static final class UserVoBuilder {
        private String name;

        public UserVoBuilder() {
        }

        public static UserVoBuilder anUserVo() {
            return new UserVoBuilder();
        }

        public UserVoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserVo build() {
            UserVo userVo = new UserVo();
            userVo.setName(name);
            return userVo;
        }
    }
}
