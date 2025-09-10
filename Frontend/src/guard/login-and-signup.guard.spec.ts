import { TestBed } from '@angular/core/testing';

import { LoginAndSignupGuard } from './login-and-signup.guard';

describe('LoginAndSignupGuard', () => {
  let guard: LoginAndSignupGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(LoginAndSignupGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
