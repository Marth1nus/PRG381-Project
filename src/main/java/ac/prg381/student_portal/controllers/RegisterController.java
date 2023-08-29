package ac.prg381.student_portal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ac.prg381.student_portal.services.RegisterService;

@RestController
@RequestMapping("/register")
public class RegisterController {

  private final RegisterService registerService;

  @Autowired
  public RegisterController(RegisterService registerService) {
    this.registerService = registerService;
  }
}
