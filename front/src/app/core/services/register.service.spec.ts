import { TestBed } from '@angular/core/testing';
import { RegisterService } from './register.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { environment } from 'src/environments/environment.prod';
import { FormControl, FormGroup } from '@angular/forms';
;

describe('RegisterService', () => {
  let service: RegisterService;
  let http: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ RegisterService]
    });
    http = TestBed.inject(HttpTestingController);
    service = TestBed.inject(RegisterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should have to register a user', () => {
    const mockUser = new FormGroup({ 
      username: new FormControl('testuser'), 
      email: new FormControl('test@example.com'), 
      password: new FormControl('Test1234@')
    });

    service.register(mockUser).subscribe((response) => {
      expect(response).toEqual(mockUser);
      const req = http.expectOne(`${environment.apiUrl}/register`); 
      expect(req.request.method).toEqual('POST');
      expect(req.request.body).toEqual(mockUser);
      req.flush(mockUser);
    });
  });
});
