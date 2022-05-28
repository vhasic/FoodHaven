import {render, screen} from "@testing-library/react";
import SignUp from "../pages/SignUp";
import userEvent from "@testing-library/user-event";


describe('SignUp', function () {
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

});