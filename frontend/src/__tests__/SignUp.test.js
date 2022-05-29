import {render, screen} from "@testing-library/react";
import SignUp from "../pages/SignUp";
import userEvent from "@testing-library/user-event";
import {act} from "react-dom/test-utils";
import axios from "axios";


describe('SignUp', function () {
    const postFun=jest.spyOn(axios,'post');

    it('Test renders correctly', function () {
        render(<SignUp/>);
        expect(screen.getByRole('heading', {
            name: /please sign up/i
        })).toHaveTextContent("Please Sign Up");
    });

    it('Test validation', function () {
        render(<SignUp/>);
        userEvent.type(screen.getByPlaceholderText(/enter first name/i),"neko");
        userEvent.type(screen.getByPlaceholderText(/enter last name/i),"nekic");
        userEvent.type(screen.getByPlaceholderText(/enter your username/i),"neko");
        userEvent.type(screen.getByPlaceholderText(/enter your email/i),"neko");
        userEvent.click(screen.getByRole('button', {
            name: /sign up/i
        }));

        expect(screen.getByText(/email address format is invalid!/i)).toHaveTextContent("Email address format is invalid!");
        expect(screen.getByText(/password is required!/i)).toHaveTextContent("Password is required!");
    });


    it('Test successfully sign on', async function () {
        postFun.mockClear().mockReturnValue(Promise.resolve({status:201}));

        render(<SignUp/>);
        userEvent.type(screen.getByPlaceholderText(/enter first name/i), "neko");
        userEvent.type(screen.getByPlaceholderText(/enter last name/i), "nekic");
        userEvent.type(screen.getByPlaceholderText(/enter your username/i), "neko");
        userEvent.type(screen.getByPlaceholderText(/enter your email/i), "neko@nesto.com");
        userEvent.type(screen.getByPlaceholderText(/enter your password/i), "Password1!");
        userEvent.type(screen.getByPlaceholderText(/confirm your password/i), "Password1!");
        await act(async () => {
            userEvent.click(screen.getByRole('button', {
                name: /sign up/i
            }));
        });

        expect(postFun).toBeCalled();
        expect(screen.getByRole('button', { name: /ok/i})).toBeEnabled();
    });

    it('Test unsuccessfully sign on', async function () {
        postFun.mockClear().mockReturnValue(Promise.reject({status:"testni status"}));

        render(<SignUp/>);
        userEvent.type(screen.getByPlaceholderText(/enter first name/i), "neko");
        userEvent.type(screen.getByPlaceholderText(/enter last name/i), "nekic");
        userEvent.type(screen.getByPlaceholderText(/enter your username/i), "neko");
        userEvent.type(screen.getByPlaceholderText(/enter your email/i), "neko@nesto.com");
        userEvent.type(screen.getByPlaceholderText(/enter your password/i), "Password1!");
        userEvent.type(screen.getByPlaceholderText(/confirm your password/i), "Password1!");
        await act(async () => {
            userEvent.click(screen.getByRole('button', {
                name: /sign up/i
            }));
        });

        expect(postFun).toBeCalled();
        expect(screen.getByRole('button', { name: /ok/i})).toBeEnabled();
    });
});