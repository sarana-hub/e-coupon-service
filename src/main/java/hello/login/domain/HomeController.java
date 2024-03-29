package hello.login.domain;

import hello.login.domain.customer.Customer;
import hello.login.domain.login.SessionConst;
import hello.login.domain.member.Member;
import hello.login.domain.customer.CustomerRepository;
import hello.login.domain.member.MemberRepository;
import hello.login.web.argumentresolver.Login;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final CustomerRepository customerRepository;
    //private final SessionManager sessionManager;

    @GetMapping("/")
    public String homeLoginArgumentResolver(@Login Member loginMember,
                                            @Login Customer loginCustomer,
                                            Model model) {

        if(loginMember != null) {
            model.addAttribute("member", loginMember);
            return "login/loginHome";
        }
        else if (loginCustomer != null) {
            model.addAttribute("customer", loginCustomer);
            return "login/loginCustomerHome";
        }
        else {
            return "home";
        }
        /*HttpSession session = request.getSession(false);
        if (session == null) {
            return "home";
        }
        //Long memberId = (Long) session.getAttribute(SessionConst.LOGIN_MEMBER);
        else if ((Long) session.getAttribute(SessionConst.LOGIN_MEMBER) != null) {
            Member member=memberRepository.findById((Long) session.getAttribute(SessionConst.LOGIN_MEMBER));
            model.addAttribute("member",member);
            return "login/loginHome";
        }
        else if ((Long) session.getAttribute(SessionConst.LOGIN_CUSTOMER) != null){
            Customer customer=customerRepository.findById((Long) session.getAttribute(SessionConst.LOGIN_CUSTOMER));

            model.addAttribute("customer", customer);
            return "login/loginHome";
        }
        else {
            return "home";
        }*/

    }

}
