import {render, screen} from "@testing-library/react";
import UserPage from "../pages/UserPage";
import AuthService from "../services/AuthService";
import UserService from "../services/UserService";
import {act} from "react-dom/test-utils";
import userEvent from "@testing-library/user-event";


describe('UserPage', function () {
    const fakeUser= {
        userId: "userId",
        firstName: "User",
        lastName: "lastName",
        username: "username",
        email: "email",
        role: {roleId: "roleId", roleName: "User"}
    };
    const getCurrentUserFun=jest.spyOn(AuthService,'getCurrentUser');
    const getUserFun=jest.spyOn(UserService,'getUser');

    beforeEach(() => {
        getCurrentUserFun.mockReturnValue({userId:fakeUser.userId});
        getUserFun.mockReturnValue(fakeUser);
    });

    it('Test renders correctly', async function () {
        await act(async () => {
            render(<UserPage/>);
        });
        expect(screen.getByText(/log out/i)).toHaveTextContent("Log Out");
    });

    it('Test gets user and renders his information correctly', async function () {
        await act(async () => {
            render(<UserPage/>);
        });
        expect(screen.getByRole('heading', {
            name: /hello user/i
        })).toHaveTextContent("Hello User");
    });
});