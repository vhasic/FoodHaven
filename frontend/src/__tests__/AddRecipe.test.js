import { getByTitle, render, screen } from "@testing-library/react";
import RecipeInfo from "../pages/RecipeInfo";
import axios from "axios";
import { act } from "react-dom/test-utils";
import UserService from "../services/UserService";
import AuthService from "../services/AuthService";
import userEvent from "@testing-library/user-event";

describe('RecipeInfo', function () {
    const fakeUser = {
        userId: "userId",
        firstName: "User",
        lastName: "lastName",
        username: "username",
        email: "email@nesto.com",
        role: { roleId: "roleId", roleName: "User" }
    };
    const getCurrentUserFun = jest.spyOn(AuthService, 'getCurrentUser');
    const getUserFun = jest.spyOn(UserService, 'getUser');
    const putFun = jest.spyOn(axios, 'post');

    beforeEach(() => {
        getCurrentUserFun.mockReturnValue({ userId: fakeUser.userId });
        getUserFun.mockReturnValue(fakeUser);
    });

    it('Test renders correctly', async function () {
        await act(async () => {
            render(<RecipeInfo />);
        });
        expect(screen.getByRole('heading', {
            name: /about/i
        })).toHaveTextContent("About");
    });

    it('Test 1', async function () {
        render(<RecipeInfo />);
        userEvent.type(screen.getByPlaceholderText(/recipe name/i), "Test recipe");
        userEvent.type(screen.getByTitle(/Value must be integer!/i), "40");
        userEvent.type(screen.getByPlaceholderText('Type recipe description here'), "This is description for Test recipe .....");
        await act(async () => {
            userEvent.click(screen.getByRole('button', {
                name: /Save recipe/i
            }));
        });
        expect(screen.getByRole('button', { name: /ok/i})).toBeEnabled();
    });

    it('Test 2', async function () {
        render(<RecipeInfo />);
        userEvent.type(screen.getByPlaceholderText(/recipe name/i), "");
        userEvent.type(screen.getByTitle(/Value must be integer!/i), "");
        userEvent.type(screen.getByPlaceholderText('Type recipe description here'), "");
        expect(screen.getByRole('warn')).toHaveTextContent('Please fill in the required information!');
    });
    
    it('Test 3', async function () {
        render(<RecipeInfo />);
        userEvent.type(screen.getByPlaceholderText(/recipe name/i), "Test recipe");
        userEvent.type(screen.getByTitle(/Value must be integer!/i), "40");
        userEvent.type(screen.getByPlaceholderText('Type recipe description here'), "This is description for Test recipe .....");
        expect(screen.getByRole('warn')).toHaveTextContent('');
    });
    
});
