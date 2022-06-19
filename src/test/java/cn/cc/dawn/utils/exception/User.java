/**
 * @Description
 * @Author everforcc
 * @Date 2022-06-08 15:12
 */

package cn.cc.dawn.utils.exception;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class User {

    @NotEmpty(message = "名称不能为空")
    @Size(min = 4, max = 32, message = "名称长度必须在{min}和{max}之间")
    private String name;

    @NotEmpty(message = "邮件地址不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @AssertTrue
    private boolean isStudent;

    public User(String name, String email, boolean isStudent) {
        this.name = name;
        this.email = email;
        this.isStudent = isStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }
}
