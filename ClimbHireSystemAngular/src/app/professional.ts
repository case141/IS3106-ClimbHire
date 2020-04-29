import { Application } from './application';
import { UserTypeEnum } from './user-type-enum.enum';

export class Professional {
	userId: number;
	password: string;
	firstName: string;
	lastName: string;
	address: string;
	email: string;
	gender: string;
	contactNumber: number;
	profilePicture: string;
	previousWorkExperiences: string[];
	skills: string[];
	qualifications: string;
	dateJoined: Date;
	dateOfBirth: Date;
	userTypeEnum: UserTypeEnum;
	jobsApplied: Application[];
	
	constructor(userId?: number, password?: string, firstName?: string, lastName?: string, address?: string, email?: string, gender?: string, 
				contactNumber?: number, dateJoined?: Date, dateOfBirth?: Date, userTypeEnum?: UserTypeEnum)
	{
		this.userId = userId;
		this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.email = email;
        this.contactNumber = contactNumber;
        this.dateJoined = dateJoined;
        this.dateOfBirth = dateOfBirth;
		this.userTypeEnum = userTypeEnum;
	}
	
}
