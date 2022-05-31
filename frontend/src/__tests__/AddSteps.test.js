import { getByRole, render, screen } from "@testing-library/react";
import Instructions from "../pages/Instructions";
import { act } from "react-dom/test-utils";
import userEvent from "@testing-library/user-event";
import axios from "axios";
import UserService from "../services/UserService";
import AuthService from "../services/AuthService";

describe('Instructions', function () {
    
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

    beforeEach(() => {
        getCurrentUserFun.mockReturnValue({userId:fakeUser.userId});
        getUserFun.mockReturnValue(fakeUser);
    });

    const postFun=jest.spyOn(axios,'post');

    it('Test 1', async function () {
        await act(async () => {
            render(<Instructions />);
        });
        expect(screen.getByRole('heading', {
            name: /Instructions/i
        })).toHaveTextContent("Instructions");
    });

    it('Test 2', function () {
        render(<Instructions />);

        for (let i = 0; i < 5; i++) {
            userEvent.click(screen.getByRole('button', {
                name: /Add step/i
            }));
        }
        expect(screen.getAllByRole('textarea')).toHaveLength(5);
        expect(screen.getAllByRole('remove-button')).toHaveLength(5);
    });

    it('Test 3', function () {
        render(<Instructions />);
        userEvent.click(screen.getByRole('button', {
            name: /Add step/i
        }));
        expect(screen.getByRole('textarea'));
        userEvent.type(screen.getByRole('textarea'), "This is a step for Test recipe .....");
        userEvent.click(screen.getByRole('remove-button'));
        expect(() => screen.getByRole('textarea')).toThrow();

    });

    it('Test 4', async function () {
        render(<Instructions />);
        userEvent.click(screen.getByRole('button', {
            name: /Add step/i
        }));
        expect(screen.getByRole('textarea'));
        userEvent.type(screen.getByRole('textarea'), "This is a step for Test recipe .....");
       
        await act(async () => {
            userEvent.click(screen.getByRole('submit-button'));
        });

        expect(postFun).toBeCalled();

    });

});