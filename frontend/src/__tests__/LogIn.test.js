import {fireEvent, render, screen} from '@testing-library/react';
import LogIn from "../pages/LogIn";
import userEvent from "@testing-library/user-event";
import * as AuthService from "../services/AuthService";
/*import * as axios from "axios";
// Mock out all top level functions, such as get, put, delete and post:
jest.mock("axios");*/

jest.mock('../services/AuthService')

describe('LogIn', function () {
    it('Test renders correctly', function () {
        render(<LogIn/>);
        expect(screen.getByTestId("mainHeader")).toHaveTextContent("FoodHaven");
    });

    it('Test renders notification if credentials are wrong', function () {
        // axios.get.mockImplementation(() => Promise.resolve(false));
        AuthService.login.mockImplementation(()=>{Promise.resolve(false)})

        render(<LogIn/>);
        userEvent.type(screen.getByPlaceholderText(/enter your username/i),"pogresanUser");
        userEvent.type(screen.getByPlaceholderText(/enter your password/i),"pogresanPassword");
        userEvent.click(screen.getByRole('button', {name: /log in/i})); //todo kako uraditi mock AuthServisa
        // screen.getByRole('button', {name: /log in/i}).click();

        expect(screen.getByRole('button', { name: /ok/i})).toBeEnabled();
        // expect(screen.getByPlaceholderText('Enter your password').value).toBe("pogresanPassword");
    });
});
