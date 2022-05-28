import {render, screen} from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import ManageAccount from "../pages/ManageAccount";


describe('ManageAccount', function () {
    it('Test renders correctly', function () {
        render(<ManageAccount/>);
        expect(screen.getByRole('heading', {
            name: /manage account/i
        })).toHaveTextContent("Manage account");
    });

    it('Test validation', function () {
        render(<ManageAccount/>);
        userEvent.click(screen.getByRole('button', {
            name: /apply/i
        }));

        expect(screen.getByText(/password is required!/i)).toHaveTextContent("Password is required!");
        expect(screen.getByText(/confirm your password!/i)).toHaveTextContent("Confirm your password!");
    });

});