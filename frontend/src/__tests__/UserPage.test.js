import {render, screen} from "@testing-library/react";
import UserPage from "../pages/UserPage";


jest.mock('../services/AuthService', () => {
   return class AuthService {
        getCurrentUser() {
            return {userId: "userId", token: "token"};
        }
    }
});

describe('UserPage', function () {
    it('Test renders correctly', function () {
        render(<UserPage/>); //todo kako mockati AuthService
        expect(screen.getByText(/log out/i)).toHaveTextContent("Log Out");
    });
});