import {render, screen} from "@testing-library/react";
import AdminPage from "../pages/AdminPage";
import AuthService from "../services/AuthService";
import UserService from "../services/UserService";
import {act} from "react-dom/test-utils";


describe('AdminPage', function () {
    const fakeUser= {
        userId: "userId",
        firstName: "Admin",
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
            render(<AdminPage/>);
        });
        expect(screen.getByText(/log out/i)).toHaveTextContent("Log Out");
    });

    it('Test gets administrator and renders his information correctly', async function () {
        await act(async () => {
            render(<AdminPage/>);
        });
        expect(screen.getByRole('heading', {
            name: /hello admin/i
        })).toHaveTextContent("Hello Admin");
    });
});