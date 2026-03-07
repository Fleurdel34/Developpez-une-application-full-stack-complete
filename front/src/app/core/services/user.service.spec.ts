import { TestBed } from '@angular/core/testing';
import { UserService } from './user.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { environment } from 'src/environments/environment.prod';
import { FormControl, FormGroup } from '@angular/forms';

describe('UserService', () => {
  let service: UserService;
  let http: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ UserService]
    });
    service = TestBed.inject(UserService);
    http = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should login', () => {
    const mocklogin = new FormGroup({ 
      email: new FormControl('test@example.com'), 
      password: new FormControl('Test1234@')
    });

    const token="token"

    service.login(mocklogin).subscribe((response) => {
      expect(response).toEqual(mocklogin);
      const req = http.expectOne(`${environment.apiUrl}/login`); 
      expect(req.request.method).toEqual('POST');
      expect(req.request.body).toEqual(mocklogin);
      req.flush(token);
    });
  });

  it('should update user', () => {
    const mockUpdateUser = new FormGroup({
      username: new FormControl('testuser'), 
      email: new FormControl('test@example.com'), 
      password: new FormControl('Test1234@')
    });

    service.update(mockUpdateUser).subscribe((response) => {
      expect(response).toEqual(mockUpdateUser);
      const req = http.expectOne(`${environment.apiUrl}/users/me`); 
      expect(req.request.method).toEqual('PUT');
      expect(req.request.body).toEqual(mockUpdateUser);
      req.flush({});
    });
  });
});
