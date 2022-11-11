package poly.edu.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import poly.edu.DAO.ChucVuDAO;
import poly.edu.DAO.KhachHangDAO;
import poly.edu.DAO.NhanVienDAO;
import poly.edu.Entity.ChucVu;
import poly.edu.Entity.KhachHang;
import poly.edu.Entity.NhanVien;

import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    BCryptPasswordEncoder pe;

    @Autowired
    KhachHangDAO khachHangDAO;

    @Autowired
    NhanVienDAO nhanVienDAO;

    @Autowired
    ChucVuDAO chucVuDAO;

    @Autowired
    HttpSession session;

    String roles;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            try {
                KhachHang user = khachHangDAO.findByEmail(username);
                NhanVien nhanVien = nhanVienDAO.findByEmail(username);
                if (nhanVien == null) {
                    ChucVu chucVu = chucVuDAO.findByMaCV(user.getMacv());
                    roles = chucVu.getTencv();

                    String password = getPasswordEncoder().encode(user.getMatkhau());
                    Map<String, Object> authentication = new HashMap<>();
                    authentication.put("user", user);
                    byte[] token = (username + ":" + user.getMatkhau()).getBytes();
                    authentication.put("token", "Basic " + Base64.getEncoder().encodeToString(token));
                    session.setAttribute("authentication", authentication);
                    session.setAttribute("email", username);
                    return User.withUsername(username).password(password).roles(roles).build();
                } else {
                    ChucVu chucVu = chucVuDAO.findByMaCV(nhanVien.getMacv());
                    roles = chucVu.getTencv();

                    String password = getPasswordEncoder().encode(nhanVien.getMatkhau());
                    Map<String, Object> authentication = new HashMap<>();
                    authentication.put("user", nhanVien);
                    byte[] token = (username + ":" + nhanVien.getMatkhau()).getBytes();
                    authentication.put("token", "Basic " + Base64.getEncoder().encodeToString(token));
                    session.setAttribute("authentication", authentication);
                    return User.withUsername(username).password(password).roles(roles).build();
                }
            } catch (NoSuchElementException e) {
                throw new UsernameNotFoundException(username + " not found!");
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/admin/**").hasAnyRole("Employee", "Admin")
                .antMatchers("/rest/**").hasRole("Admin")
                .anyRequest().permitAll();

        http.formLogin()
                .loginPage("/security/login/form")
                .loginProcessingUrl("/security/login")
                .defaultSuccessUrl("/security/login/success", false)
                .failureUrl("/security/login/error");

        http.rememberMe()
                .tokenValiditySeconds(86400);

        http.exceptionHandling()
                .accessDeniedPage("/security/unauthoried");

        http.logout()
                .logoutUrl("/security/logoff")
                .logoutSuccessUrl("/security/logoff/success");
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}
