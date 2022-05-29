import {render, screen} from '@testing-library/react';
import LogIn from "../pages/LogIn";
import userEvent from "@testing-library/user-event";
import AuthService from "../services/AuthService";
import {act} from "react-dom/test-utils";
import UserService from "../services/UserService";


describe('LogIn', function () {
    const login=jest.spyOn(AuthService,'login');
    const getUser=jest.spyOn(UserService,'getUser');

    it('Test renders correctly', function () {
        render(<LogIn/>);
        expect(screen.getByTestId("mainHeader")).toHaveTextContent("FoodHaven");
    });

    it('Test renders notification if credentials are wrong', async function () {
        login.mockReturnValue(false);

        render(<LogIn/>);
        userEvent.type(screen.getByPlaceholderText(/enter your username/i), "pogresanUser");
        userEvent.type(screen.getByPlaceholderText(/enter your password/i), "pogresanPassword");
        await act(async () => { //ovo se mora raditi ako se mijenja stanje komponente
            userEvent.click(screen.getByRole('button', {name: /log in/i}));
        });

        expect(login).toBeCalledTimes(1);
        expect(screen.getByRole('button', { name: /ok/i})).toBeEnabled();
    });

    it('Test redirects to UserPage if logged user role is user', async function () {
        login.mockClear().mockReturnValue(true);
        const fakeUser= {
            userId: "userId",
            firstName: "User",
            lastName: "lastName",
            username: "username",
            email: "email",
            role: {roleId: "roleId", roleName: "User"}
        };
        getUser.mockReturnValue(fakeUser);

        render(<LogIn/>);
        Object.defineProperty(window, 'location', {
            value: { assign: jest.fn() }
        });

        userEvent.type(screen.getByPlaceholderText(/enter your username/i), "user");
        userEvent.type(screen.getByPlaceholderText(/enter your password/i), "Password1!");
        //ovo se mora raditi ako se mijenja stanje komponente
        await act(async () => {
            userEvent.click(screen.getByRole('button', {name: /log in/i}));
        });

        expect(getUser).toBeCalled();
        expect(window.location.assign).toBeCalled();
    });
});
