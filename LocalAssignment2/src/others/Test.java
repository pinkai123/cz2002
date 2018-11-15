
				//check whether weightage has been added
				Weightage Weightage = tempCourse.getCourseWeightage();
				if(Weightage == null) {
					System.out.println("Please enter the Weightage first!");
					break;
				}
				//check for existing result or not construct
				Result result = Database.getResult(matric, courseID);
				if(result == null) {
					result = new Result(tempCourse,student);
				}
				//coursework without subcomponent
				if(!Weightage.getHaveSub()) {
					System.out.println("This course has CourseWork without Subcomponent.");
					System.out.println("Enter mark for coursework(0 -1): ");
					double mark = sc.nextDouble();
					if(mark > 1|| mark < 0) {
						System.out.println("Enter Mark from 0 - 1");
						break;
					}
					result.addGrade(gradeType.COURSEWORK, null, mark);
					Database.addResult(result);
					System.out.println("Result added successfully");
				}
				//coursework with subcomponent
				else {
					System.out.println("This course has CourseWork with Subcomponents.");
					ArrayList<Subcomponent> subcomponents = Weightage.getSubcomponent();
					for(int i = 0;i < subcomponents.size(); i++) {
						System.out.println("Enter the mark for " + subcomponents.get(i).getName() + "(0 -1):");
						double mark = sc.nextDouble();
						if(mark > 1|| mark < 0) {
							System.out.println("Enter Mark from 0 - 1");
							break;
						}
						result.addGrade(gradeType.COURSEWORK, subcomponents.get(i).getName(), mark);
						Database.addResult(result);
						System.out.println("Result of" + subcomponents.get(i).getName() +"added successfully");
					}
				}