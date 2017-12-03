import { TestBed, inject } from '@angular/core/testing';

import { LoginGuardChildService } from './login.guard.child.service';

describe('Login.Guard.ChildService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LoginGuardChildService]
    });
  });

  it('should be created', inject([LoginGuardChildService], (service: LoginGuardChildService) => {
    expect(service).toBeTruthy();
  }));
});
