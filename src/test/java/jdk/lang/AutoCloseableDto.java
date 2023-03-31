/**
 * @Description
 * @Author everforcc
 * @Date 2023-03-30 15:04
 * Copyright
 */

package jdk.lang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AutoCloseableDto implements AutoCloseable {

    private String str;

    public void m() {
        System.out.println("随便执行个方法");
    }

    @Override
    public void close() throws Exception {
        System.out.println("执行释放方法:" + str);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutoCloseableDto that = (AutoCloseableDto) o;
        return Objects.equals(str, that.str);
    }

    @Override
    public int hashCode() {
        return Objects.hash(str);
    }

    @Override
    public String toString() {
        return "AutoCloseableDto{" +
                "str='" + str + '\'' +
                '}';
    }
}
