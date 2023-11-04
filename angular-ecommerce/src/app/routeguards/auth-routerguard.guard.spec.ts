import { TestBed } from '@angular/core/testing';

import { AuthRouterguardGuard } from './auth-routerguard.guard';

describe('AuthRouterguardGuard', () => {
  let guard: AuthRouterguardGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(AuthRouterguardGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
