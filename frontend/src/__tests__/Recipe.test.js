import {render, screen } from "@testing-library/react";
import Recipe from "../pages/Recipe";
import { act } from "react-dom/test-utils";
import axios from "axios";
import UserService from "../services/UserService";
import AuthService from "../services/AuthService";

describe('Recipe', function () {

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

    it('Test 1', async function () {
        render(<Recipe/>);
        expect(screen.getByRole('heading', {
            name: /Reviews/i
        })).toHaveTextContent("Reviews");    

        expect(screen.getByRole('heading', {
            name: /Ingredients/i
        })).toHaveTextContent("Ingredients"); 

        expect(screen.getByRole('heading', {
            name: /Instructions/i
        })).toHaveTextContent("Instructions");

        expect(() => screen.getByPlaceholderText('Write your review here')).toThrow();
        expect(() => screen.getByRole('your-rating-label')).toThrow();
    });


    it('Test 2', async function () {
        getCurrentUserFun.mockReturnValue({userId:fakeUser.userId});
        getUserFun.mockReturnValue(fakeUser);
        render(<Recipe/>);
        expect(screen.getByRole('your-rating-label')).toHaveTextContent("Your Rating"); 
        expect(screen.getByPlaceholderText('Write your review here'));
    });

});