import {render, screen} from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import ManageAccount from "../pages/ManageAccount";
import axios from "axios";
import {act} from "react-dom/test-utils";
import UserService from "../services/UserService";
import AuthService from "../services/AuthService";


describe('ManageAccount', function () {
    const fakeUser= {
        userId: "userId",
        firstName: "User",
        lastName: "lastName",
        username: "username",
        email: "email@nesto.com",
        role: {roleId: "roleId", roleName: "User"}
    };
    const getCurrentUserFun=jest.spyOn(AuthService,'getCurrentUser');
    const getUserFun=jest.spyOn(UserService,'getUser');
    const putFun=jest.spyOn(axios,'put');

    beforeEach(() => {
        getCurrentUserFun.mockReturnValue({userId:fakeUser.userId});
        getUserFun.mockReturnValue(fakeUser);
    });

    it('Test renders correctly', async function () {
        await act(async () => {
            render(<ManageAccount/>);
        });
        expect(screen.getByRole('heading', {
            name: /manage account/i
        })).toHaveTextContent("Manage account");
    });

    it('Test validation', async function () {
        await act(async () => {
            render(<ManageAccount/>);
        });
        userEvent.click(screen.getByRole('button', {
            name: /apply/i
        }));

        expect(screen.getByText(/password is required!/i)).toHaveTextContent("Password is required!");
        expect(screen.getByText(/confirm your password!/i)).toHaveTextContent("Confirm your password!");
    });

    it('Test successfully changed account', async function () {
        putFun.mockClear().mockReturnValue(Promise.resolve({status:200}));

        await act(async () => {
            render(<ManageAccount/>);
        });

        userEvent.type(screen.getByPlaceholderText(/enter your password/i), "Password1!");
        userEvent.type(screen.getByPlaceholderText(/confirm your password/i), "Password1!");
        await act(async () => {
            userEvent.click(screen.getByRole('button', {
                name: /apply/i
            }));
        });

        expect(putFun).toBeCalled();
        expect(screen.getByRole('button', { name: /ok/i})).toBeEnabled();
    });
});